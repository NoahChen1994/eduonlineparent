package com.eduonline.eduvideo.service.impl;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.DeleteVideoResponse;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.eduonline.eduvideo.service.VideoService;
import com.eduonline.eduvideo.utils.AliyunVodSDKUtils;
import com.eduonline.eduvideo.utils.ConstantPropertiesUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.vod.upload.impl.UploadAttachedMediaImpl;
import com.aliyun.vod.upload.impl.UploadImageImpl;
import com.aliyun.vod.upload.impl.UploadM3u8FileImpl;
import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.*;
import com.aliyun.vod.upload.resp.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

@Service
public class VideoServiceImpl implements VideoService {

    /**
     * 上传视频到阿里云
     * @param file
     * @return 视频id
     */
    @Override
    public String uploadAliyunVideo(MultipartFile file) {

        String videoId = null;
        try {
            //获取文件名称  xxx.mp4
            String fileName = file.getOriginalFilename();
            //截取标题
            String title = fileName.substring(0,fileName.lastIndexOf("."));
            //获取流
            InputStream inputStream = file.getInputStream();
            UploadStreamRequest request = new UploadStreamRequest(ConstantPropertiesUtil.ACCESS_KEY_ID, ConstantPropertiesUtil.ACCESS_KEY_SECRET,
                    title, fileName, inputStream);

            UploadVideoImpl uploader = new UploadVideoImpl();
            UploadStreamResponse response = uploader.uploadStream(request);
            if (response.isSuccess()) {
                videoId = response.getVideoId();
            } else {
                videoId = response.getVideoId();
            }
        } catch (IOException e) {
            return null;
        }

        return videoId;
    }

    /**
     * 按照id删除阿里云视频
     * @param videoId
     */
    @Override
    public void deleteAliyunVideoById(String videoId) {
        try {
            //初始化客户端
            DefaultAcsClient client = AliyunVodSDKUtils.initVodClient
                    (ConstantPropertiesUtil.ACCESS_KEY_ID, ConstantPropertiesUtil.ACCESS_KEY_SECRET);

            //创建删除视频请求对象
            DeleteVideoRequest request = new DeleteVideoRequest();

            //设置要删除视频的id
            request.setVideoIds(videoId);

            //调用方法实现删除
            DeleteVideoResponse response = client.getAcsResponse(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据视频id集合批量删除视频
     * @param list
     */
    @Override
    public void deleteMoreVideoByIds(List<String> list) {
        try {
            //初始化客户端
            DefaultAcsClient client = AliyunVodSDKUtils.initVodClient
                    (ConstantPropertiesUtil.ACCESS_KEY_ID, ConstantPropertiesUtil.ACCESS_KEY_SECRET);

            //创建删除视频请求对象
            DeleteVideoRequest request = new DeleteVideoRequest();

            String ids = StringUtils.join(list.toArray(), ",");

            //设置要删除视频的id
            request.setVideoIds(ids);

            //调用方法实现删除
            DeleteVideoResponse response = client.getAcsResponse(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getPlayAuth(String videoId) {
        String playAuth = null;
        try {
            //初始化客户端、请求对象和相应对象
            DefaultAcsClient client = AliyunVodSDKUtils.initVodClient(ConstantPropertiesUtil.ACCESS_KEY_ID,
                    ConstantPropertiesUtil.ACCESS_KEY_SECRET);
            GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
            GetVideoPlayAuthResponse response = new GetVideoPlayAuthResponse();
            //设置请求参数
            request.setVideoId(videoId);
            //获取请求响应
            response = client.getAcsResponse(request);

            //输出请求结果
            //播放凭证
            playAuth = response.getPlayAuth();
        } catch (Exception e) {

        }
        return playAuth;
    }
}
