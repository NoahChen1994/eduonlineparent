package com.eduonline.edustatistics.client;

import com.eduonline.common.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("eduucenter")
@Component
public interface UcenterClient {
    @GetMapping("/eduucenter/getRegistNumByDay/{day}")
    public R getRegistNumByDay(@PathVariable("day") String day);
}
