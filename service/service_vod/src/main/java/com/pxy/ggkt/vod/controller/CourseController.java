package com.pxy.ggkt.vod.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pxy.ggkt.model.vod.Course;
import com.pxy.ggkt.result.Result;
import com.pxy.ggkt.vo.vod.CourseFormVo;
import com.pxy.ggkt.vo.vod.CoursePublishVo;
import com.pxy.ggkt.vo.vod.CourseQueryVo;
import com.pxy.ggkt.vod.service.CourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author pxy
 * @since 2023-03-19
 */
@Api("Course Controller")
@RestController
@RequestMapping("/admin/vod/course")
public class CourseController {
    @Autowired
    private CourseService courseService;


    @ApiOperation("添加课程基本信息")
    @PostMapping("save")
    public Result save(@RequestBody CourseFormVo courseFormVo) {
        Long courseId = courseService.saveCourseInfo(courseFormVo);
        return Result.ok(courseId);

    }

    @ApiOperation("点播课程列表")
    @GetMapping("{page}/{limit}")
    public Result courseList(@PathVariable Long page,
                             @PathVariable Long limit,
                             CourseQueryVo courseQueryVo) {
        Page<Course> pageParam = new Page<>(page, limit);
        Map<String, Object> map = courseService.findPageCourse(pageParam, courseQueryVo);
        return Result.ok(map);
    }


    //根据id获取课程信息
    @GetMapping("get/{id}")
    public Result get(@PathVariable Long id) {
        CourseFormVo courseFormVo =  courseService.getCourseInfoById(id);
        return Result.ok(courseFormVo);

    }

    //修改课程信息
    @PostMapping("update")
    public Result update (@RequestBody CourseFormVo courseFormVo){
        courseService.updateCourseId(courseFormVo);
        //course id

        return Result.ok(courseFormVo.getId());

    }


    //根据课程id查询发布的课程信息
    @ApiOperation("id查询发布的课程信息")
    @GetMapping("getCoursePublishVo/{id}")
    public Result getCoursePublishVo(@PathVariable Long id){
       CoursePublishVo coursePublishVo =  courseService.getCoursePublishVo(id);
       return Result.ok(coursePublishVo);
    }

    //课程的最终发布
    @ApiOperation("课程最终发布")
    @PutMapping("publishCourse/{id}")
    public Result publishCourse(@PathVariable Long id) {
        courseService.publishCourse(id);
        return Result.ok(null);
    }

    //删除课程
    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable Long id) {
        courseService.removeCourseId(id);
        return Result.ok(null);

    }

}

