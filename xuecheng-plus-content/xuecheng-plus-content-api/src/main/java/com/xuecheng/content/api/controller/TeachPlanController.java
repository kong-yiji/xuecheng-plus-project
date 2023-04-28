package com.xuecheng.content.api.controller;

import com.xuecheng.content.model.dto.SaveTeachplanDto;
import com.xuecheng.content.model.dto.TeachplanDto;
import com.xuecheng.content.service.TeachPlanService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        List<TeachplanDto>teachplanDtos= teachPlanService.findTeachplanTree(courseId);
        System.out.println(teachplanDtos);
        return teachplanDtos;
    }


    @ApiOperation("课程计划创建或修改")
    @PostMapping("/teachplan")
    public void saveTeachPlan(@RequestBody SaveTeachplanDto saveTeachplanDto){
        teachPlanService.saveTeachplan(saveTeachplanDto);
    }

    @ApiOperation("删除章节")
    @DeleteMapping("/teachplan/{Id}")
    public void deleteTeachPlan(@PathVariable Long Id){
    teachPlanService.deleteTeachPlan(Id);
    }

   @ApiOperation("课程上移")
   @PostMapping("/teachplan/moveup/{Id}")
    public void moveUp(@PathVariable Long Id){
     teachPlanService.moveUp(Id);
    }

    @ApiOperation("课程下移")
    @PostMapping("/teachplan/movedown/{Id}")
    public void moveDown(@PathVariable Long Id){
        teachPlanService.moveDown(Id);
    }
}
