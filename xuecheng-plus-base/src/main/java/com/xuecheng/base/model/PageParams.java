package com.xuecheng.base.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : yin
 * @Date: 2023/4/23
 * @Description:分页查询通用参数类
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PageParams {
    private Long PageNo=1L;
    private Long PageSize=10L;
}
