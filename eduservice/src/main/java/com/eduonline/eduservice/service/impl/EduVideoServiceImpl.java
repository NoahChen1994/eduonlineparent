package com.eduonline.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.eduonline.eduservice.entity.EduVideo;
import com.eduonline.eduservice.mapper.EduVideoMapper;
import com.eduonline.eduservice.service.EduVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

    /**
     * 根据课程id删除
     * @param courseId
     */
    @Override
    public void deleteVideoById(String courseId) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("course_id",courseId);
        baseMapper.delete(wrapper);
    }

    /**
     * 根据课程id查询小节信息
     * @param id
     * @return
     */
    @Override
    public List<EduVideo> getVideoInfoById(String id) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("course_id",id);
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
