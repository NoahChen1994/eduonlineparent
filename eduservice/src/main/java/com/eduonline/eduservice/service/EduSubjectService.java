package com.eduonline.eduservice.service;

import com.eduonline.eduservice.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.eduonline.eduservice.vo.SubjectNestedVo;
import com.eduonline.eduservice.vo.SubjectVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author Chenxinyi
 * @since 2020-02-18
 */
public interface EduSubjectService extends IService<EduSubject> {

    /**
     * 读取excel内容
     * @param file
     */
    List<String> importSubject(MultipartFile file);

    List<SubjectNestedVo> getNestedList();
}
