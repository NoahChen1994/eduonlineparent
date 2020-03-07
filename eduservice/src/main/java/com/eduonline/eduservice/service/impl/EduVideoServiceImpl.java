package com.eduonline.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.eduonline.eduservice.client.VideoClient;
import com.eduonline.eduservice.entity.EduVideo;
import com.eduonline.eduservice.mapper.EduVideoMapper;
import com.eduonline.eduservice.service.EduVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author Chenxinyi
 * @since 2020-02-18
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {

    //注入client
    @Autowired
    private VideoClient videoClient;

    /**
     * 根据课程id删除
     * @param courseId
     */
    @Override
    public void deleteVideoById(String courseId) {
        //删除视频
        //先根据课程id查询所有的视频集合
        QueryWrapper<EduVideo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id",courseId);
        queryWrapper.select("video_source_id");
        List<EduVideo> eduVideos = baseMapper.selectList(queryWrapper);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < eduVideos.size() ; i++) {
            EduVideo video = eduVideos.get(i);
            String videoSourceId = video.getVideoSourceId();
            list.add(videoSourceId);
        }
        if(list.size()>0){
            //调用服务端删除视频
            videoClient.deleteMoreVideoByIds(list);
        }

        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("course_id",courseId);
        baseMapper.delete(wrapper);
    }

    /**
     * 根据课程id查询小节信息
     * @param courseId
     * @return
     */
    @Override
    public List<EduVideo> getVideoInfoById(String courseId) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("course_id",courseId);
        return baseMapper.selectList(wrapper);
    }

    /**
     * 根据章节id查询小节信息
     * @param id
     * @return
     */
    @Override
    public List<EduVideo> getVideoById(String id) {
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("chapter_id",id);
        return baseMapper.selectList(wrapper);
    }

    /**
     * 添加小节
     * @param video
     * @return
     */
    @Override
    public boolean addVideo(EduVideo video) {
        int result = baseMapper.insert(video);
        return result>0;
    }

    /**
     * 根据小节id删除小节
     * @param id
     * @return
     */
    @Override
    public boolean deleteVideo(String id) {

        //删除小节时还要删除阿里云视频
        //先查询视频id
        EduVideo eduVideo = baseMapper.selectById(id);
        String videoSourceId = eduVideo.getVideoSourceId();
        if(!StringUtils.isEmpty(videoSourceId)){
            //调用客户端删除小节视频
            videoClient.deleteAliyunVideoById(videoSourceId);
        }

        //删除小节
        int result = baseMapper.deleteById(id);
        return result>0;
    }

    /**
     * 修改小节
     * @param video
     * @return
     */
    @Override
    public boolean updateVideo(EduVideo video) {
        int result = baseMapper.updateById(video);
        return result>0;
    }

    /**
     * 根据小节id查询
     * @param id
     * @return
     */
    @Override
    public EduVideo getVideoByVideoId(String id) {
        return baseMapper.selectById(id);
    }
}
