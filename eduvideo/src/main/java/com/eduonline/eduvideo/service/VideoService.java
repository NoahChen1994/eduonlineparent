package com.eduonline.eduvideo.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface VideoService {

    //上传视频到阿里云
    String uploadAliyunVideo(MultipartFile file);

    //按照id删除阿里云视频
    void deleteAliyunVideoById(String videoId);

    void deleteMoreVideoByIds(List<String> list);
}
