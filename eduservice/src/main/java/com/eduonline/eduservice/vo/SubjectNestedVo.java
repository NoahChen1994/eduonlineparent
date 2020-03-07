package com.eduonline.eduservice.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 课程类别一级分类列表
 */
@ApiModel(value="课程分类信息", description="后台课程发布页需要的相关字段")
@Data
public class SubjectNestedVo {

    @ApiModelProperty(value = "一级分类id")
    private String id;

    @ApiModelProperty(value = "一级分类名称")
    private String title;

    @ApiModelProperty(value = "二级分类集合")
    private List<SubjectVo> children = new ArrayList<>();
}
