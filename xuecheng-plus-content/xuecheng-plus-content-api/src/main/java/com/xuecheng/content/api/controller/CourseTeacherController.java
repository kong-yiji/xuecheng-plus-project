package com.xuecheng.content.api.controller;

import com.xuecheng.content.model.po.CourseTeacher;
import com.xuecheng.content.service.CourseTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author : yin
 * @Date: 2023/4/29
 * @Description:TODO
 */
@Api("教师管理接口")
@RestController
public class CourseTeacherController {

    @Autowired
    CourseTeacherService courseTeacherService;

    @ApiOperation("通过课程id查找教师")
    @GetMapping("/courseTeacher/list/{courseId}")
    public List <CourseTeacher> getTeacherByCourseId(@PathVariable Long courseId){
      return courseTeacherService.getTeachersByCourseId(courseId);
    }

    @ApiOperation("添加/修改教师信息")
    @PostMapping("/courseTeacher")
    public void saveCourseTeacher(@RequestBody CourseTeacher courseTeacher){

        courseTeacherService.saveTeacher(courseTeacher);
    }



    @ApiOperation("根据教师id,课程id删除教师信息")
    @DeleteMapping("/courseTeacher/course/{courseId}/{Id}")
    public void deleteCourseTeacher(@PathVariable Long courseId,@PathVariable Long Id){
           courseTeacherService.deleteCourseTeacher(courseId,Id);
    }
}
