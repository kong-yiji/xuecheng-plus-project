package com.xuecheng.content.service;

import com.xuecheng.base.model.PageParams;
import com.xuecheng.base.model.PageResult;
import com.xuecheng.content.model.dto.QueryCourseParamsDto;
import com.xuecheng.content.model.po.CourseBase;

/***
 * @author yin
 *  2023/4/24  11:25
 * @author yin
 * @Description 课程基本信息管理
 */
public interface CourseBaseInfoService {
    PageResult<CourseBase>queryBaseList(PageParams pageParams, QueryCourseParamsDto queryCourseParamsDto);
}
