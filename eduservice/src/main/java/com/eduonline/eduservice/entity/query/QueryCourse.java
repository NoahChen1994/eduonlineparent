package com.eduonline.eduservice.entity.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 课程信息条件查询封装类
 */
@ApiModel(value = "Course查询对象", description = "课程查询对象封装")
@Data
public class QueryCourse {

    @ApiModelProperty(value = "一级类别id")
    String  subjectParentId;

    @ApiModelProperty(value = "二级类别id")
    String  subjectId;
    @ApiModelProperty(value = "课程名称")
    String title;

    @ApiModelProperty(value = "讲师id")
    String teacherId;

}
