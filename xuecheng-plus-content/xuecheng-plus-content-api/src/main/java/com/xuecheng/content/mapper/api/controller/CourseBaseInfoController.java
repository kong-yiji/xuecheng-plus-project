package com.xuecheng.content.api.controller;

import com.xuecheng.base.model.PageParams;
import com.xuecheng.base.model.PageResult;
import com.xuecheng.content.model.dto.QueryCourseParamsDto;
import com.xuecheng.content.model.po.CourseBase;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * @author : yin
 * @Date: 2023/4/23
 * @Description:课程信息编辑接口
 */
@RestController
public class CourseBaseInfoController {
    @ApiOperation("页面查询")
    @PostMapping("/course/list")
    public PageResult<CourseBase> list(PageParams pageparams,@RequestBody(required = false) QueryCourseParamsDto queryCourseParams) {
        return null;
    }
}
