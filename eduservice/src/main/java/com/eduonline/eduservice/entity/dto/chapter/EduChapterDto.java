package com.eduonline.eduservice.entity.dto.chapter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 章节Dto
 */
@ApiModel(value = "小节信息")
@Data
public class EduChapterDto {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "章节ID")
    private String id;

    @ApiModelProperty(value = "章节标题")
    private String title;

    @ApiModelProperty(value = "小节集合")
    private List<EduVideoDto> children = new ArrayList<>();
}
