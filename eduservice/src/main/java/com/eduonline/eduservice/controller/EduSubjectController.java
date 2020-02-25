package com.eduonline.eduservice.controller;


import com.eduonline.common.R;
import com.eduonline.eduservice.service.EduSubjectService;
import com.eduonline.eduservice.vo.SubjectNestedVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author Chenxinyi
 * @since 2020-02-18
 */
@RestController
@RequestMapping("/eduservice/subject")
@CrossOrigin
public class EduSubjectController {

    @Autowired
    EduSubjectService eduSubjectService;

    /**
     * 上传excel获取文件内容
     * @param file
     * @return
     */
    @PostMapping("importExcelSubject")
    public R importExcelSubject(MultipartFile file){

        List<String> msg = eduSubjectService.importSubject(file);
        if(msg.size()==0){
            //所有数据都上传成功
            return R.ok().message("批量导入成功！");
        }else {
            //部分数据上传成功 或者上传失败
            return R.error().data("msgList",msg).message("部分数据导入失败！");
        }
    }

    @GetMapping("getNestedList")
    public R getNestedList(){
        List<SubjectNestedVo> nestedList = eduSubjectService.getNestedList();
        return R.ok().data("items",nestedList);
    }
}

