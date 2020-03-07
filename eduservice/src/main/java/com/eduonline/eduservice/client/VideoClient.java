package com.eduonline.eduservice.client;

import com.eduonline.common.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("eduvideo") //发现服务端
@Component
public interface VideoClient {

    //定义调用方法
    //方法调用的路径
    //按照id删除阿里云视频
    @DeleteMapping("/eduvideo/deleteAliyunVideoById/{videoId}")
    public R deleteAliyunVideoById(@PathVariable("videoId") String videoId);

    //按照视频id集合删除
    @DeleteMapping("/eduvideo/deleteMoreVideoByIds")
    public R deleteMoreVideoByIds(@RequestParam("list") List list);
}
