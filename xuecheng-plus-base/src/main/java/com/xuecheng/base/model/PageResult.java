package com.xuecheng.content.api.api.base.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author : yin
 * @Date: 2023/4/23
 * @Description:分页查询结果模型类
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PageResult<T> implements Serializable {
    //数据列表
    private List<T> items;
    //总记录数
    private long counts;
    //当前页码
    private long page;
    //每页记录数
    private long PageSize;
}
