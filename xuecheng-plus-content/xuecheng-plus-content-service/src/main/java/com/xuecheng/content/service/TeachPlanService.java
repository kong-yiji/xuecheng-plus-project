package com.xuecheng.content.service;

import com.xuecheng.content.model.dto.SaveTeachplanDto;
import com.xuecheng.content.model.dto.TeachplanDto;

import java.util.List;

/**
 * @author : yin
 * @Date: 2023/4/27
 * @Description:课程计划管理
 */
public interface TeachPlanService {
    List<TeachplanDto> findTeachplanTree(long courseId);

    void saveTeachplan(SaveTeachplanDto teachplanDto);

    void deleteTeachPlan(Long courseId);

    void moveUp(Long courseId);

    void moveDown(Long id);
}
