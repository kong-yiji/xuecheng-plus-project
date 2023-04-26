package com.xuecheng.content.api.controller;

import com.xuecheng.base.exception.ValidationGroups;
import com.xuecheng.content.service.CourseBaseInfoService;
import com.xuecheng.content.model.dto.AddCourseDto;
import com.xuecheng.content.model.dto.CourseBaseInfoDto;
import com.xuecheng.base.model.PageParams;
import com.xuecheng.base.model.PageResult;
import com.xuecheng.content.model.dto.QueryCourseParamsDto;
import com.xuecheng.content.model.po.CourseBase;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author : yin
 * @Date: 2023/4/23
 * @Description:课程信息编辑接口
 */
@RestController
public class CourseBaseInfoController {
    @Autowired
    CourseBaseInfoService courseBaseInfoService;
    @ApiOperation("页面查询")
    @PostMapping("/course/list")
    public PageResult<CourseBase> list(PageParams pageparams,@RequestBody(required = false) QueryCourseParamsDto queryCourseParams) {
        PageResult<CourseBase> pageResult=
        courseBaseInfoService.queryBaseList(pageparams, queryCourseParams);
        System.out.println("---------"+pageResult);
        return pageResult;
    }


    @ApiOperation("新增课程基础信息")
    @PostMapping("/course")
    public CourseBaseInfoDto createCourseBase(@RequestBody  @Validated(ValidationGroups.Insert.class) AddCourseDto addCourseDto){
       long companyId=1232141425L;
       return courseBaseInfoService.createCourseBase(companyId,addCourseDto);
    }
}
