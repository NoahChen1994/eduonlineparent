package com.eduonline.eduvideo.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface VideoService {

    //上传视频到阿里云
    String uploadAliyunVideo(MultipartFile file);

    //按照id删除阿里云视频
    void deleteAliyunVideoById(String videoId);

    //批量删除视频
    void deleteMoreVideoByIds(List<String> list);

    //根据视频id获取播放凭证
    String getPlayAuth(String videoId);
}
