package com.xuecheng.content.api.api.content.controller;

import com.xuecheng.content.dto.CourseCategoryTreeDto;
import com.xuecheng.content.api.api.content.service.CourseCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author : yin
 * @Date: 2023/4/25
 * @Description:TODO
 */
@RestController
@Slf4j
public class CourseCategoryController {
    @Autowired
    CourseCategoryService categoryService;
    @GetMapping("/course-category/tree-nodes")
    public List<CourseCategoryTreeDto> categoryTreeDtoList(){
        return categoryService.queryTreeNodes("1");
    }
}
