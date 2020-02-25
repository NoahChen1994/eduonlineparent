package com.eduonline.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.eduonline.eduservice.entity.EduSubject;
import com.eduonline.eduservice.exception.EduException;
import com.eduonline.eduservice.mapper.EduSubjectMapper;
import com.eduonline.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.eduonline.eduservice.vo.SubjectNestedVo;
import com.eduonline.eduservice.vo.SubjectVo;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author Chenxinyi
 * @since 2020-02-18
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    @Autowired
    EduSubjectMapper subjectMapper;


    /**
     * 导入excel数据
     * @param file
     * @return
     */
    @Override
    public List<String> importSubject(MultipartFile file) {
        //定义错误消息
        List<String> msg = new ArrayList<>();
        try {

            InputStream is = file.getInputStream();

            //07版本workbook
            Workbook workbook = new XSSFWorkbook(is);

            //获取sheet
            Sheet sheet = workbook.getSheetAt(0);

            //获取最后一行的索引
            int lastRowNum = sheet.getLastRowNum();

            //得到每一行 第一行表头不需要添加到数据库
            for (int i = 1; i <= lastRowNum; i++) {
                //得到每一行
                Row row = sheet.getRow(i);

                //行为空
                if (row == null) {
                    //没数据
                    String str1 =  "第"+(i+1)+"行第1列数据为空，请检查数据表格！";
                    String str2 =  "第"+(i+1)+"行第2列数据为空，请检查数据表格！";
                    msg.add(str1);
                    msg.add(str2);
                    //跳出添加
                     continue;
                }

                //第一列的数据 一级分类
                Cell cellone = row.getCell(0);
                //列为空
                if (cellone == null) {
                    String str = "第"+(i+1)+"行第1列数据为空，请检查数据表格！";
                    //跳过当前列
                    msg.add(str);
                    continue;
                }
                //获取第一列中数据
                String valueone = cellone.getStringCellValue();

                //第二列的数据 二级分类
                Cell celltwo = row.getCell(1);
                if (celltwo == null) {
                    String str = "第"+(i+1)+"行第2列数据为空，请检查数据表格！";
                    msg.add(str);
                    //跳过当前列
                    continue;
                }
                String valuetwo = celltwo.getStringCellValue();

                //添加一级分类
                //先判断该分类在数据库里是否存在
                String parentId = null;
                EduSubject existOneSubject = this.existOneSubject(valueone);
                if (existOneSubject == null) {//不存在 添加
                    EduSubject subjectOne = new EduSubject();
                    subjectOne.setParentId("0");
                    subjectOne.setTitle(valueone);
                    subjectOne.setSort(0);
                    subjectMapper.insert(subjectOne);
                    parentId = subjectOne.getId();
                } else {//存在 不添加 赋值一级分类的id
                    parentId = existOneSubject.getId();
                }

                //添加二级分类
                EduSubject existTwoSubject = this.existTwoSubject(valuetwo, parentId);
                if (existTwoSubject == null) { //不存在 添加
                    EduSubject subjectTwo = new EduSubject();
                    subjectTwo.setParentId(parentId);
                    subjectTwo.setTitle(valuetwo);
                    subjectTwo.setSort(0);
                    subjectMapper.insert(subjectTwo);
                }
            }
        } catch (Exception e) {
            EduException eduException = new EduException();
            eduException.setCode(2001);
            eduException.setMessage("出现了异常，数据导入失败");
            throw eduException ;
        }
        return msg;
    }

    /**
     * 查询所有的分类
     * @return
     */
    @Override
    public List<SubjectNestedVo> getNestedList() {
        //要返回的集合
        List<SubjectNestedVo> nestedList = new ArrayList<>();

        Map<String,SubjectNestedVo> map = new HashMap<>();

        //查询所有的分类
        List<EduSubject> eduSubjects = subjectMapper.selectList(null);

        //遍历获得每一个分类
        for (EduSubject eduSubject : eduSubjects) {
            SubjectNestedVo subjectNestedVo = new SubjectNestedVo();
            subjectNestedVo.setId(eduSubject.getId());
            subjectNestedVo.setTitle(eduSubject.getTitle());
            map.put(eduSubject.getId(),subjectNestedVo);
            //取得一级目录
            if(eduSubject.getParentId().equals("0")){
                subjectNestedVo.setId(eduSubject.getId());
                subjectNestedVo.setTitle(eduSubject.getTitle());
                nestedList.add(subjectNestedVo);
            }else {//子目录
                //拿到父菜单的id
                String parentId = eduSubject.getParentId();
                //拿到父类
                SubjectNestedVo parent= map.get(parentId);
                //拿到父菜单的子菜单
                List<SubjectVo> children = parent.getChildren();

                SubjectVo vo =new SubjectVo();
                vo.setId(eduSubject.getId());
                vo.setTitle(eduSubject.getTitle());
                children.add(vo);
                parent.setChildren(children);
            }

        }
        return nestedList;
    }

    //判断是否存在一级目录
    private EduSubject existOneSubject(String name) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", name);
        wrapper.eq("parent_id", "0");
        return baseMapper.selectOne(wrapper);
    }

    //判断是否存在二级目录
    private EduSubject existTwoSubject(String name, String parentId) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", name);
        wrapper.eq("parent_id", parentId);
        return baseMapper.selectOne(wrapper);
    }
}

