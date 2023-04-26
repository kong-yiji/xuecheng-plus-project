package com.xuecheng.content.api.api.content.service;

import com.xuecheng.content.dto.CourseCategoryTreeDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface CourseCategoryService {
     List<CourseCategoryTreeDto> queryTreeNodes(String id);
}
