package com.eduonline.eduservice.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 自定义异常
 */

@Data
@NoArgsConstructor//无参构造
@AllArgsConstructor//有参构造
public class EduException extends RuntimeException {

    private Integer code;//状态码

    private String message;//异常信息


}
