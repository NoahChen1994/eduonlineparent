package com.eduonline.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.eduonline.eduservice.entity.EduCourse;
import com.eduonline.eduservice.entity.EduCourseDescription;
import com.eduonline.eduservice.entity.dto.form.EduAllCourseInfoDto;
import com.eduonline.eduservice.entity.dto.form.EduCourseInfoDto;
import com.eduonline.eduservice.entity.query.QueryCourse;
import com.eduonline.eduservice.exception.EduException;
import com.eduonline.eduservice.mapper.EduCourseMapper;
import com.eduonline.eduservice.service.EduChapterService;
import com.eduonline.eduservice.service.EduCourseDescriptionService;
import com.eduonline.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.eduonline.eduservice.service.EduVideoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author Chenxinyi
 * @since 2020-02-18
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    //操作课程描述表
    @Autowired
    EduCourseDescriptionService descriptionService;

    //操作小节表
    @Autowired
    EduVideoService videoService;

    //操作章节表
    @Autowired
    EduChapterService chapterService;

    /**
     * 添加课程信息
     * @param eduCourseInfoDto
     * @return
     */
    @Override
    public String insertCourseInfo(EduCourseInfoDto eduCourseInfoDto) {
        //课程信息添加到课程表中
        EduCourse eduCourse = new EduCourse();
        //数据复制 对应字段自动复制
        BeanUtils.copyProperties(eduCourseInfoDto,eduCourse);
        int result = baseMapper.insert(eduCourse);

        //基本信息添加失败抛出异常 不执行之后的操作
        if (result==0){
            throw new EduException(20001,"课程信息添加失败");
        }

        //课程描述信息添加到描述课程表中
        //获取描述信息
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        String description = eduCourseInfoDto.getDescription();
        eduCourseDescription.setDescription(description);
        //eduCourseDescription id字段注解 更改为 @TableId(value = "id", type = IdType.INPUT)
        String courseId = eduCourse.getId();
        eduCourseDescription.setId(courseId);
        boolean flag = descriptionService.save(eduCourseDescription);

        return courseId;
    }

    /**
     * 通过id查询课程信息
     * @param id
     * @return
     */
    @Override
    public EduCourseInfoDto getCourseInfoById(String id) {
        //查询两张表
        //根据id查询基本信息
        EduCourse eduCourse = baseMapper.selectById(id);
        if(eduCourse==null){
            throw new EduException(20001,"没有该课程信息");
        }
        //填充基本信息
        EduCourseInfoDto eduCourseInfoDto = new EduCourseInfoDto();
        BeanUtils.copyProperties(eduCourse, eduCourseInfoDto);

        //根据id查询描述信息
        EduCourseDescription eduCourseDescription = descriptionService.getById(id);
        String description = eduCourseDescription.getDescription();
        //填充课程描述信息
        eduCourseInfoDto.setDescription(description);

        return eduCourseInfoDto;
    }

    /**
     * 修改课程信息
     * @param eduCourseInfoDto
     * @return
     */
    @Override
    public String updateCourseInfo(EduCourseInfoDto eduCourseInfoDto) {
        //修改课程基本信息
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(eduCourseInfoDto,eduCourse);
        int result = baseMapper.updateById(eduCourse);
        if(result==0){
            throw new EduException(20001,"课程信息修改失败");
        }

        //修改课程描述信息
        EduCourseDescription description = new EduCourseDescription();
        BeanUtils.copyProperties(eduCourseInfoDto,description);
        boolean flag = descriptionService.updateById(description);
        return eduCourse.getId();
    }

    /**
     * 分页带条件查询
     * @param coursePage
     * @param query
     * @return
     */
    @Override
    public IPage<EduCourse> getCondtionPageList(Page<EduCourse> coursePage, QueryCourse query) {

        QueryWrapper<EduCourse> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("gmt_create");
        //无条件分页查询
        if(query==null){
           return baseMapper.selectPage(coursePage, queryWrapper);
        }

        //有条件拼接条件
        String subjectId = query.getSubjectId();
        String subjectParentId = query.getSubjectParentId();
        String teacherId = query.getTeacherId();
        String title = query.getTitle();
        if(!StringUtils.isEmpty(subjectId)){
            queryWrapper.eq("subject_id",subjectId);
        }
        if(!StringUtils.isEmpty(subjectParentId)){
            queryWrapper.eq("subject_parent_id",subjectParentId);
        }
        if(!StringUtils.isEmpty(teacherId)){
            queryWrapper.eq("teacher_id",teacherId);
        }
        if(!StringUtils.isEmpty(title)){
            queryWrapper.eq("title",title);
        }
        return baseMapper.selectPage(coursePage, queryWrapper);
    }

    /**
     * 根据id删除课程信息
     * @param courseId
     */
    @Override
    public boolean deleteCourseById(String courseId) {

            //删除小节信息
            videoService.deleteVideoById(courseId);

            //删除章节信息
            chapterService.deleteChapterById(courseId);

            //删除课程描述信息
            descriptionService.removeById(courseId);

            //删除课程信息 其它信息可能为空 所以只有课程本身删除的结果能作为判别依据
            int i = baseMapper.deleteById(courseId);

            return i>0 ;
    }

    /**
     * 根据课程id查询课程详细信息
     * @param id
     * @return
     */
    @Override
    public EduAllCourseInfoDto getAllCourseInfoById(String id) {
        EduAllCourseInfoDto courseInfo = baseMapper.getAllCourseInfoById(id);
        return courseInfo;
    }

    /**
     * 根据课程id发布课程
     * @param id
     * @return
     */
    @Override
    public boolean updateCourseStatus(String id) {
        EduCourse eduCourse = baseMapper.selectById(id);
        eduCourse.setStatus("Normal");
        int result = baseMapper.updateById(eduCourse);
        return result>0;
    }
}
