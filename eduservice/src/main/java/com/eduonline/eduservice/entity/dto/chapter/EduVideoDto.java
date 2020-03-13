package com.eduonline.eduservice.entity.dto.chapter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 列表Dto
 */
@ApiModel(value = "小节信息")
@Data
public class EduVideoDto {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "小节ID")
    private String id;

    @ApiModelProperty(value = "小节标题")
    private String title;

    @ApiModelProperty(value = "是否可以试听：0免费 1收费")
    private String free;

    @ApiModelProperty(value = "视频id")
    private String videoSourceId;
}
