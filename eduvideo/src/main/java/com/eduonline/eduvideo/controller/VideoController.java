package com.eduonline.eduvideo.controller;

import com.eduonline.common.R;
import com.eduonline.eduvideo.service.VideoService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@CrossOrigin
@RestController
@RequestMapping("/eduvideo")

public class VideoController {

    @Autowired
    VideoService videoService;

    /**
     * 上传视频到阿里云
     * @param file
     * @return
     */
    @PostMapping("uploadAliyunVideo")
    public R uploadAliyunVideo(MultipartFile file){
        String videoId = videoService.uploadAliyunVideo(file);
        return R.ok().data("items",videoId);
    }

    /**
     * 按照id删除阿里云视频
     * @param videoId
     * @return
     */
    @DeleteMapping("deleteAliyunVideoById/{videoId}")
    public R deleteAliyunVideoById(@PathVariable String videoId){
        videoService.deleteAliyunVideoById(videoId);
        return R.ok();
    }


    /**
     * 按照视频id批量删除
     * @param list
     * @return
     */
    @DeleteMapping("deleteMoreVideoByIds")
    public R deleteMoreVideoByIds(@RequestParam("list") List list){
        videoService.deleteMoreVideoByIds(list);
        return R.ok();
    }

    /**
     * 根据视频id获取播放凭证
     * @return
     */
    @GetMapping("getPlayAuth/{videoId}")
    public R getPlayAuth(@PathVariable String videoId){
        String playAuth = videoService.getPlayAuth(videoId);
        return R.ok().data("playAuth",playAuth);
    }
}
