package com.xuecheng.content.service;

import com.xuecheng.content.model.po.CourseTeacher;

import java.util.List;

public interface CourseTeacherService {
    List<CourseTeacher> getTeachersByCourseId(Long courseId);

    void saveTeacher(CourseTeacher courseTeacher);



    void deleteCourseTeacher(Long courseId, Long id);
}
