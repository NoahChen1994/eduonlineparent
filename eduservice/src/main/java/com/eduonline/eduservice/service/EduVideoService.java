package com.eduonline.eduservice.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.eduonline.eduservice.entity.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author Chenxinyi
 * @since 2020-02-18
 */
public interface EduVideoService extends IService<EduVideo> {

    /**
     * 根据课程id删除
     * @param id
     */
    void deleteVideoById(String id);

    /**
     * 根据课程id查询
     */
    List<EduVideo> getVideoInfoById(String id);

    //根据章节id查询
    List<EduVideo>  getVideoById(String id);

    //添加章节
    boolean addVideo(EduVideo video);

    //删除小节
    boolean deleteVideo(String id);

    //修改小节
    boolean updateVideo(EduVideo video);

    //根据小节id查询
    EduVideo getVideoByVideoId(String id);
}
