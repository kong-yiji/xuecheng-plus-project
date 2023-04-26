package com.xuecheng.content.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : yin
 * @Date: 2023/4/23
 * @Description:课程查询参数
 */
@AllArgsConstructor
@Data
@NoArgsConstructor
public class QueryCourseParamsDto {
    //审核状态
    private String auditStatus;
    //课程名称
    private String courseName;
    //发布状态
    private String publishStatus;
}
