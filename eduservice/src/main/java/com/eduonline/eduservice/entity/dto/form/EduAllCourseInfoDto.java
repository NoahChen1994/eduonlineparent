package com.eduonline.eduservice.entity.dto.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 课程详细信息封装类
 */
@ApiModel(value = "课程详细信息", description = "课程详细信息封装类")
@Data
public class EduAllCourseInfoDto {
    @ApiModelProperty(value = "课程ID")
    private String id;

    @ApiModelProperty(value = "课程名称")
    private String title;

    @ApiModelProperty(value = "课程价格")
    private String price;

    @ApiModelProperty(value = "课程封面")
    private String cover;

    @ApiModelProperty(value = "课程描述")
    private String description;

    @ApiModelProperty(value = "课程一级分类")
    private String levelOne;

    @ApiModelProperty(value = "课程二级分类")
    private String levelTwo;

    @ApiModelProperty(value = "讲师姓名")
    private String teacherName;

    @ApiModelProperty(value = "课时")
    private String lesson;





}
