package com.xuecheng.content.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xuecheng.base.exception.XueChengPlusException;
import com.xuecheng.content.mapper.TeachplanMapper;
import com.xuecheng.content.mapper.TeachplanMediaMapper;
import com.xuecheng.content.model.dto.SaveTeachplanDto;
import com.xuecheng.content.model.dto.TeachplanDto;
import com.xuecheng.content.model.po.Teachplan;
import com.xuecheng.content.model.po.TeachplanMedia;
import com.xuecheng.content.service.TeachPlanService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author : yin
 * @Date: 2023/4/27
 * @Description:TODO
 */
@Service
public class TeachPlanServiceImpl implements TeachPlanService {
    @Resource
    TeachplanMapper teachplanMapper;
    @Resource
    TeachplanMediaMapper teachplanMediaMapper;

    @Override
    public List<TeachplanDto> findTeachplanTree(long courseId) {
        return teachplanMapper.selectTreeNodes(courseId);
    }


    @Override
    public void saveTeachplan(SaveTeachplanDto teachplanDto) {
        //课程计划id
        Long id = teachplanDto.getId();
        //修改课程计划
        if (id != null) {
            Teachplan teachplan = teachplanMapper.selectById(id);
            BeanUtils.copyProperties(teachplanDto, teachplan);
            teachplanMapper.updateById(teachplan);
        } else {
            //取出同父同级别的课程计划数量
            int count = getTeachplanCount(teachplanDto.getCourseId(), teachplanDto.getParentid());
            Teachplan teachplanNew = new Teachplan();
            //为0直接加，否则最大值加一
            if (count == 0) {
                teachplanNew.setOrderby(1);
            } else {
                int max = getMaxOrder(teachplanDto.getCourseId(), teachplanDto.getParentid());
                teachplanNew.setOrderby(max + 1);
            }
            //设置排序号

            BeanUtils.copyProperties(teachplanDto, teachplanNew);

            teachplanMapper.insert(teachplanNew);
        }
    }


    /***
     * @author yin
     *  2023/4/28  16:40
     * @author yin
     * @params [courseId]
     * @return void
     * @description 删除课程, 大章节必须保证没有小章节，小章节直接删除，并且删除相关媒资信息
     */
    @Transactional
    @Override
    public void deleteTeachPlan(Long Id) {
        Teachplan teachplan = teachplanMapper.selectById(Id);
        if (teachplan != null && teachplan.getGrade() == 1) {
            LambdaQueryWrapper<Teachplan> queryWrapper = new LambdaQueryWrapper();
            queryWrapper.eq(Teachplan::getParentid, Id);
            Integer count = teachplanMapper.selectCount(queryWrapper);
            if (count > 0)
                throw new XueChengPlusException("课程计划信息还有子级信息，无法操作");
            else teachplanMapper.deleteById(Id);
        }
        teachplanMapper.deleteById(Id);
        LambdaQueryWrapper<TeachplanMedia> queryWrapper1 = new LambdaQueryWrapper<>();
        queryWrapper1.eq(TeachplanMedia::getTeachplanId, Id);
        teachplanMediaMapper.delete(queryWrapper1);
    }

    /***
     * @author yin
     *  2023/4/28  18:36
     * @author yin
     * @parans [courseId]
     * @return void
     * @Description:课程小节上移(和上一章互换位置orderby)
     */
    @Override
    public void moveUp(Long Id) {
        Teachplan teachplan = teachplanMapper.selectById(Id);
        Teachplan teachplan2 = getPreTeachPlan(teachplan);
        if(teachplan2==null)return;
        int tmp2=teachplan.getOrderby();
        int tmp1=teachplan2.getOrderby();
        teachplan.setOrderby(tmp1);
        teachplan2.setOrderby(tmp2);
        teachplanMapper.updateById(teachplan);
        teachplanMapper.updateById(teachplan2);
    }

    @Override
    public void moveDown(Long Id) {
        Teachplan teachplan = teachplanMapper.selectById(Id);
        Teachplan teachplan2 = getNextTeachPlan(teachplan);
        if(teachplan2==null)return;
        int tmp2=teachplan.getOrderby();
        int tmp1=teachplan2.getOrderby();
        teachplan.setOrderby(tmp1);
        teachplan2.setOrderby(tmp2);
        teachplanMapper.updateById(teachplan);
        teachplanMapper.updateById(teachplan2);
    }
//获取下一条数据
    private Teachplan getNextTeachPlan(Teachplan teachplan) {
        LambdaQueryWrapper<Teachplan> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.gt(Teachplan::getOrderby, teachplan.getOrderby());
        queryWrapper.orderByAsc(Teachplan::getOrderby).last("limit 1");
        return teachplanMapper.selectOne(queryWrapper);
    }

    private Teachplan getPreTeachPlan(Teachplan teachplan) {
        LambdaQueryWrapper<Teachplan> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.lt(Teachplan::getOrderby, teachplan.getOrderby());
        queryWrapper.orderByDesc(Teachplan::getOrderby).last("limit 1");
       return teachplanMapper.selectOne(queryWrapper);
    }

    //获取子章节数量
    private int getTeachplanCount(Long courseId, Long parentId) {
        LambdaQueryWrapper<Teachplan> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(Teachplan::getCourseId, courseId);
        queryWrapper.eq(Teachplan::getParentid, parentId);
        return teachplanMapper.selectCount(queryWrapper);
    }

    //获取最后一个order
    private int getMaxOrder(Long courseId, Long parentId) {
        return teachplanMapper.getMaxOrder(courseId, parentId);
    }
}
