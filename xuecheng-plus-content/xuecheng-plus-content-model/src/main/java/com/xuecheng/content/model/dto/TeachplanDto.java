package com.xuecheng.content.model.dto;

import com.xuecheng.content.model.po.Teachplan;
import com.xuecheng.content.model.po.TeachplanMedia;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author : yin
 * @Date: 2023/4/27
 * @Description:TODO
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TeachplanDto extends Teachplan {
    TeachplanMedia teachplanMedia;
    List<TeachplanDto>teachPlanTreeNodes;
}
