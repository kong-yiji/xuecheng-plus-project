package com.xuecheng.content.api.controller;

import com.xuecheng.base.model.PageParams;
import com.xuecheng.base.model.PageResult;
import com.xuecheng.content.model.dto.QueryCourseParamsDto;
import com.xuecheng.content.model.po.CourseBase;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : yin
 * @Date: 2023/4/23
 * @Description:课程信息编辑接口
 */
@RestController
public class CourseBaseInfoController {
    @PostMapping("/course/list")
public PageResult<CourseBase> list(PageParams pageparams, QueryCourseParamsDto queryCourseParams){
    return null;
}
}
