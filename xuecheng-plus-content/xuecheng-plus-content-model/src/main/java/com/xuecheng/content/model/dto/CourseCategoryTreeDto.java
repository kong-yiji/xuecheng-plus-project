package com.xuecheng.content.model.dto;

import com.xuecheng.content.model.po.CourseCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author : yin
 * @Date: 2023/4/25
 * @Description:TODO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseCategoryTreeDto extends CourseCategory implements Serializable {
    private List<CourseCategoryTreeDto>childrenTreeNodes;
}
