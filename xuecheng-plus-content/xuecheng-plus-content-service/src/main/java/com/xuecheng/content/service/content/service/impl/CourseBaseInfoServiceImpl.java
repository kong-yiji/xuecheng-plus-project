package com.xuecheng.content.api.api.content.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xuecheng.content.api.api.content.mapper.CourseBaseMapper;
import com.xuecheng.content.api.api.content.mapper.CourseCategoryMapper;
import com.xuecheng.content.api.api.content.mapper.CourseMarketMapper;
import com.xuecheng.content.dto.AddCourseDto;
import com.xuecheng.content.dto.CourseBaseInfoDto;
import com.xuecheng.base.model.PageParams;
import com.xuecheng.base.model.PageResult;
import com.xuecheng.content.dto.QueryCourseParamsDto;
import com.xuecheng.content.po.CourseBase;
import com.xuecheng.content.po.CourseCategory;
import com.xuecheng.content.po.CourseMarket;
import com.xuecheng.content.api.api.content.service.CourseBaseInfoService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author : yin
 * @Date: 2023/4/24
 * @Description:TODO
 */
@Service
public class CourseBaseInfoServiceImpl  implements CourseBaseInfoService {
    @Autowired
    CourseBaseMapper courseBaseMapper;
    @Autowired
    CourseMarketMapper courseMarketMapper;
    @Autowired
    CourseCategoryMapper courseCategoryMapper;
    @Override
    public PageResult<CourseBase> queryBaseList(PageParams pageParams, QueryCourseParamsDto queryCourseParamsDto) {
        //构建查询条件对象
        LambdaQueryWrapper<CourseBase> queryWrapper = new LambdaQueryWrapper<>();
        //构建查询条件，根据课程名称查询
        queryWrapper.like(StringUtils.isNotEmpty(queryCourseParamsDto.getCourseName()),CourseBase::getName,queryCourseParamsDto.getCourseName());
        //构建查询条件，根据课程审核状态查询
        queryWrapper.eq(StringUtils.isNotEmpty(queryCourseParamsDto.getAuditStatus()),CourseBase::getAuditStatus,queryCourseParamsDto.getAuditStatus());
//构建查询条件，根据课程发布状态查询
//todo:根据课程发布状态查询

        //分页对象
        Page<CourseBase> page = new Page<>(pageParams.getPageNo(), pageParams.getPageSize());
        // 查询数据内容获得结果
        Page<CourseBase> pageResult = courseBaseMapper.selectPage(page, queryWrapper);
        // 获取数据列表
        List<CourseBase> list = pageResult.getRecords();
        // 获取数据总数
        long total = pageResult.getTotal();
        // 构建结果集
        PageResult<CourseBase> courseBasePageResult = new PageResult<>(list, total, pageParams.getPageNo(), pageParams.getPageSize());
        return courseBasePageResult;

    }

    @Transactional
    @Override
    public CourseBaseInfoDto createCourseBase(long companyId, AddCourseDto dto) {
        if(StringUtils.isBlank(dto.getName())){
            throw  new RuntimeException("课程名称为空");
        }
        if(StringUtils.isBlank(dto.getMt())){
            throw  new RuntimeException("课程分类为空");
        }
        if(StringUtils.isBlank(dto.getSt())){
            throw new RuntimeException("课程分类为空");
        }
        if(StringUtils.isBlank(dto.getGrade())){
            throw  new RuntimeException("课程等级为空");
        }
        if(StringUtils.isBlank(dto.getTeachmode())){
            throw new RuntimeException("教育模式为空");
        }
        if(StringUtils.isBlank(dto.getUsers())){
            throw new RuntimeException("适用人群为空");
        }
        if(StringUtils.isBlank(dto.getCharge())){
            throw new RuntimeException("收费规则为空");
        }
        CourseBase courseBase=new CourseBase();
        BeanUtils.copyProperties(dto,courseBase);
        courseBase.setAuditStatus("202002");
        courseBase.setStatus("203001");
        courseBase.setCompanyId(companyId);
        courseBase.setCreateDate(LocalDateTime.now());
        int insert=courseBaseMapper.insert(courseBase);
        if(insert<=0){
            throw new RuntimeException("添加新课程基本信息失败");
        }
        //todo:向课程营销表保存课程营销信息
        CourseMarket courseMarketNew=new CourseMarket();
        long courseId=courseBase.getId();
        BeanUtils.copyProperties(dto,courseMarketNew);
        courseMarketNew.setId(courseId);
        int i=saveCourseMarket(courseMarketNew);
        if(i<=0){
            throw new RuntimeException("保存课程营销信息失败");
        }
       //todo:查询课程基本信息及营销信息并返回
       return getCourseBaseInfo(courseId);
    }


    private int saveCourseMarket(CourseMarket courseMarket){
    String charge=courseMarket.getCharge();
    if(StringUtils.isBlank(charge)) {
        throw new RuntimeException("收费标准没选择");
    }
        if(charge.equals("201001")){
            if(courseMarket.getPrice()==null||courseMarket.getPrice().floatValue()<=0){
                throw new RuntimeException("课程收费价格不能为空且大于0");
            }
        }
        CourseMarket tmp=courseMarketMapper.selectById(courseMarket.getId());
        if(tmp==null){
            return courseMarketMapper.insert(courseMarket);
        }
        else{
            BeanUtils.copyProperties(courseMarket,tmp);
            tmp.setId(courseMarket.getId());
            return courseMarketMapper.insert(tmp);
        }
    }


    public CourseBaseInfoDto getCourseBaseInfo(long courseId){
        CourseBase courseBase=courseBaseMapper.selectById(courseId);
        if(courseBase==null)return null;
        CourseMarket courseMarket=courseMarketMapper.selectById(courseId);
        CourseBaseInfoDto courseBaseInfoDto = new CourseBaseInfoDto();
        BeanUtils.copyProperties(courseBase,courseBaseInfoDto);
        if(courseMarket != null){
            BeanUtils.copyProperties(courseMarket,courseBaseInfoDto);
        }
        CourseCategory courseCategoryBySt=courseCategoryMapper.selectById(courseBase.getSt());
        courseBaseInfoDto.setStName(courseCategoryBySt.getName());
        CourseCategory courseCategoryByMt=courseCategoryMapper.selectById(courseBase.getMt());
        courseBaseInfoDto.setMtName(courseCategoryByMt.getName());
        return courseBaseInfoDto;
    }
}
