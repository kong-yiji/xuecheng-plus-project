package com.xuecheng.content.api.api.content.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xuecheng.content.dto.CourseCategoryTreeDto;
import com.xuecheng.content.po.CourseCategory;

import java.util.List;

/**
 * <p>
 * 课程分类 Mapper 接口
 * </p>
 *
 * @author itcast
 */
public interface CourseCategoryMapper extends BaseMapper<CourseCategory> {
List<CourseCategoryTreeDto> selectTreeNodes(String id);
}
