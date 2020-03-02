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

    //读取excel内容
    List<String> importSubject(MultipartFile file);

    //获取所有分类
    List<SubjectNestedVo> getNestedList();

    //删除一级分类
    boolean deleteSubjectById(String id);

    //添加一级分类
    boolean addLevelOneSubject(EduSubject eduSubject);

    //添加二级分类
    boolean addLevelTwoSubject(EduSubject eduSubject);
}
