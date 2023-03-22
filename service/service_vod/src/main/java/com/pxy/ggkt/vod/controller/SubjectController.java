package com.pxy.ggkt.vod.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.pxy.ggkt.model.vod.Subject;
import com.pxy.ggkt.result.Result;
import com.pxy.ggkt.vod.service.SubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author pxy
 * @since 2023-03-18
 */

@Api(tags ="tree classification")
@RestController
@RequestMapping("/admin/vod/subject")
//@CrossOrigin
public class SubjectController {
    @Autowired
    private SubjectService subjectService;

    //lazy 加载 实现树形展示表
    @ApiOperation("Course Classification Table ")
    @GetMapping("getChildSubject/{id}")
    public Result getChildSubject(@PathVariable Long id) {
        List<Subject> subjectList = subjectService.selectSubjectList(id);
        return Result.ok(subjectList);
    }

    //课程分类导出功能
    @ApiOperation(value = "Course Classification export")
    @GetMapping("exportData")
    public void exportData(HttpServletResponse response) {
        subjectService.exportData(response);
    }

    //course classification import
    @ApiOperation("course classification import")
    @PostMapping("importData")
    public Result importData(MultipartFile file) {
        subjectService.importData(file);
        return Result.ok(null);

    }

}

