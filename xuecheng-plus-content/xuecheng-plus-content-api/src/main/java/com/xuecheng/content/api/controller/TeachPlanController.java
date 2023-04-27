package com.xuecheng.content.api.controller;

import com.xuecheng.content.model.dto.TeachplanDto;
import com.xuecheng.content.service.TeachPlanService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author : yin
 * @Date: 2023/4/27
 * @Description:TODO
 */
@Api(value = "课程计划编辑接口", tags = "课程计划编辑接口")
@RestController
public class TeachPlanController {

    @Autowired
    TeachPlanService teachPlanService;

    @ApiOperation("查询课程计划树形结构")
//    @ApiImplicitParam(value = "courseId", dataType = "Long", required = true,
//            paramType = "path")
    @GetMapping("/teachplan/{courseId}/tree-nodes")
    public List<TeachplanDto> getTreeNodes(@PathVariable Long courseId) {
        return teachPlanService.findTeachplanTree(courseId);
    }
}
