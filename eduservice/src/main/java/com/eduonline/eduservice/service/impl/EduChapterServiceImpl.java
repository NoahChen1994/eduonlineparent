package com.eduonline.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.eduonline.eduservice.entity.EduChapter;
import com.eduonline.eduservice.entity.EduVideo;
import com.eduonline.eduservice.entity.dto.chapter.EduChapterDto;
import com.eduonline.eduservice.entity.dto.chapter.EduVideoDto;
import com.eduonline.eduservice.exception.EduException;
import com.eduonline.eduservice.mapper.EduChapterMapper;
import com.eduonline.eduservice.service.EduChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.eduonline.eduservice.service.EduVideoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author Chenxinyi
 * @since 2020-02-18
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {

    @Autowired
    EduVideoService videoService;

    /**
     * 根据课程id删除章节
     * @param courseId
     */
    @Override
    public void deleteChapterById(String courseId) {

        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("course_id",courseId);
        baseMapper.delete(wrapper);
    }

    /**
     * 根据课程id查询所有章节信息
     * @return
     */
    @Override
    public List<EduChapterDto> getChapterInfoById(String courseId) {

        //要返回的章节集合
        List<EduChapterDto> chapterNestedList = new ArrayList<>();

        //查询章节信息
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("course_id", courseId);
        List<EduChapter> chapterList = baseMapper.selectList(wrapper);

        //查询小节信息
        List<EduVideo> videoList = videoService.getVideoInfoById(courseId);

        //遍历获得每一个章节
        for (EduChapter chapter : chapterList) {
            EduChapterDto chapterDto = new EduChapterDto();
            BeanUtils.copyProperties(chapter,chapterDto);
            List<EduVideoDto> children = chapterDto.getChildren();
            for (EduVideo video : videoList) {
                if(video.getChapterId().equals(chapterDto.getId())){
                    EduVideoDto videoDto = new EduVideoDto();
                    BeanUtils.copyProperties(video,videoDto);
                    children.add(videoDto);
                }
            }
            chapterNestedList.add(chapterDto);
        }

        return chapterNestedList;
    }

    /**
     * 添加章节
     * @param chapter
     * @return
     */
    @Override
    public boolean addChapter(EduChapter chapter) {
        int result = baseMapper.insert(chapter);
        return result>0;
    }

    /**
     * 修改章节
     * @param chapter
     * @return
     */
    @Override
    public boolean updateChapter(EduChapter chapter) {
        int result = baseMapper.updateById(chapter);
        return result>0;
    }

    /**
     * 根据id删除章节
     * @return
     */
    @Override
    public boolean deleteChapterByChapterId(String id) {

        //先查询是否有小节
        List<EduVideo> videoInfo = videoService.getVideoById(id);
        if(videoInfo.size()>0){
            throw new EduException(20001,"请先删除小节");
        }

        int result = baseMapper.deleteById(id);
        return result>0;
    }

    /**
     * 根据id查询章节信息
     * @param id
     * @return
     */
    @Override
    public EduChapter getChapterByChapterId(String id) {
        EduChapter eduChapter = baseMapper.selectById(id);
        return eduChapter;
    }
}
