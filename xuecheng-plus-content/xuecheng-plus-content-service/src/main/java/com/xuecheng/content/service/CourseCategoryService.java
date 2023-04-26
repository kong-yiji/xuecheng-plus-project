package com.xuecheng.content.service;

import com.xuecheng.content.model.dto.CourseCategoryTreeDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface CourseCategoryService {
     List<CourseCategoryTreeDto> queryTreeNodes(String id);
}
