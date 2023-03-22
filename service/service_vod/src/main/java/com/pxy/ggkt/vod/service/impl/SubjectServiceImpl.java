package com.pxy.ggkt.vod.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.pxy.ggkt.exception.GgktException;
import com.pxy.ggkt.model.vod.Subject;
import com.pxy.ggkt.vo.vod.SubjectEeVo;
import com.pxy.ggkt.vod.listener.SubjectListener;
import com.pxy.ggkt.vod.mapper.SubjectMapper;
import com.pxy.ggkt.vod.service.SubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author pxy
 * @since 2023-03-18
 */
@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements SubjectService {

    @Autowired
    private SubjectListener subjectListener;

    //以id来查询父级节点
    @Override
    public List<Subject> selectSubjectList(Long id) {
        QueryWrapper<Subject> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", id);
        List<Subject> subjectList = baseMapper.selectList(wrapper);
        //循环遍历 判断是否有下一层数据
        for (Subject subject :
                subjectList) {
            Long subjectId = subject.getId();
            boolean hasChildren = this.hasChildren(subjectId);
            subject.setHasChildren(hasChildren);
        }
        return subjectList;
    }


    private boolean hasChildren(Long subjectId) {
        QueryWrapper<Subject> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", subjectId);
        Integer count = baseMapper.selectCount(wrapper);
        return count > 0;
    }

    //课程分类导出功能
    @Override
    public void exportData(HttpServletResponse response) {
        try {
            //Set download information
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            //URL.encode可以防止中文乱码，当然和easyexcel没有关系
            String fileName = URLEncoder.encode("课程分类", "UTF-8");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
            List<Subject> subjectList = baseMapper.selectList(null);
            List<SubjectEeVo> subjectEevoList = new ArrayList<>(subjectList.size());
            //进行写操作

            for (Subject subject : subjectList) {
                SubjectEeVo subjectEeVo = new SubjectEeVo();
//                subjectEeVo.setId(subject.getId());
//                subjectEeVo.setParentId(subject.getParentId());
                BeanUtils.copyProperties(subject, subjectEeVo);
                subjectEevoList.add(subjectEeVo);
            }
            EasyExcel.write(response.getOutputStream(), SubjectEeVo.class)
                    .sheet("课程分类")
                    .doWrite(subjectEevoList);

        } catch (Exception e) {
            throw new GgktException(20001, "导出失败");
        }
    }

    //course classification import
    @Override
    public void importData(MultipartFile file) {
        try {
            EasyExcel.read(file.getInputStream(),
                    SubjectEeVo.class,
                    subjectListener)
                    .sheet().doRead();
        } catch (IOException e) {
            throw new GgktException(20001,"导入失败！");
        }
    }
}
