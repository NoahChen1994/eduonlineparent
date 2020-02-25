package com.eduonline.eduservice.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 一级分类列表
 */
@Data
public class SubjectNestedVo {
    private String id;
    private String title;
    private List<SubjectVo> children = new ArrayList<>();
}
