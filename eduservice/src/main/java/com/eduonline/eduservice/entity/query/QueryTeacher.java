package com.eduonline.eduservice.entity.query;

/**
 * 查询条件封装类
 */

import lombok.Data;

@Data
public class QueryTeacher {
    private String name;
    private String leval;
    private String begin;
    private String end;
}
