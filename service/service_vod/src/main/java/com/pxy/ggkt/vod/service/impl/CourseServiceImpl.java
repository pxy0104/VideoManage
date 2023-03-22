package com.pxy.ggkt.vod.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pxy.ggkt.model.base.BaseEntity;
import com.pxy.ggkt.model.vod.Course;
import com.pxy.ggkt.model.vod.CourseDescription;
import com.pxy.ggkt.model.vod.Subject;
import com.pxy.ggkt.model.vod.Teacher;
import com.pxy.ggkt.result.Result;
import com.pxy.ggkt.vo.vod.CourseFormVo;
import com.pxy.ggkt.vo.vod.CoursePublishVo;
import com.pxy.ggkt.vo.vod.CourseQueryVo;
import com.pxy.ggkt.vod.mapper.CourseMapper;
import com.pxy.ggkt.vod.service.*;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author pxy
 * @since 2023-03-19
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

    @Autowired
    private TeacherService teacherService;
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private CourseDescriptionService descriptionService;


    @Autowired
    private VideoService videoService;

    @Autowired
    private ChapterService chapterService;


    @Override
    public Long saveCourseInfo(CourseFormVo courseFormVo) {
        //添加课程的基本信息，操作course表
        Course course = new Course();
        BeanUtils.copyProperties(courseFormVo, course);
        baseMapper.insert(course);
        //添加课程基本信息，操作course_description表
        CourseDescription courseDescription = new CourseDescription();
        courseDescription.setDescription(courseFormVo.getDescription());

        //设置课程id ----------------------
        courseDescription.setId(course.getId());
//        courseDescription.setCourseId(course.getId());
        descriptionService.save(courseDescription);
        return course.getId();
    }

    //根据id查询课程信息
    @Override
    public CourseFormVo getCourseInfoById(Long id) {
        Course course = baseMapper.selectById(id);
        if (course == null) {
            return null;
        }
//        QueryWrapper<CourseDescription> wrapper = new QueryWrapper<>();
//        wrapper.eq("course_id", id);
        CourseDescription courseDescription  = descriptionService.getById(id);
//        CourseDescription courseDescription = descriptionService.getOne(wrapper);
        CourseFormVo courseFormVo = new CourseFormVo();
        BeanUtils.copyProperties(course,courseFormVo);
        if (courseDescription != null) {
            courseFormVo.setDescription(courseDescription.getDescription());
        }
        return courseFormVo;
    }

    //修改课程信息
    @Override
    public void updateCourseId(CourseFormVo courseFormVo) {
        //修改课程基本信息
        Course course = new Course();
        BeanUtils.copyProperties(courseFormVo,course);
        baseMapper.updateById(course);
        //修改课程描述信息
        CourseDescription description = new CourseDescription();
        description.setDescription(courseFormVo.getDescription());
        //设置课程描述id
        description.setId(course.getId());
        descriptionService.updateById(description);
    }


    //根据课程id查询发布的课程信息
    @Override
    public CoursePublishVo getCoursePublishVo(Long id) {
        return baseMapper.selectCoursePublishVoById(id);
    }

    //最终发布课程
    @Override
    public void publishCourse(Long id) {
        Course course = baseMapper.selectById(id);
        course.setStatus(1);
        course.setPublishTime(new Date());
        baseMapper.updateById(course);
    }

    //删除课程
    @Override
    public void removeCourseId(Long id) {
        //根据课程id删除小节
        videoService.removeVideoByCourseId(id);
        //根据课程id删除章节
        chapterService.removeChapterByCourseId(id);
        //根据课程id删除描述
        descriptionService.removeById(id);
        //根据课程id删除课程
        baseMapper.deleteById(id);
    }


    @Override
    public Map<String, Object> findPageCourse(Page<Course> pageParam, CourseQueryVo courseQueryVo) {
        //获取条件值
        String title = courseQueryVo.getTitle();
        Long subjectId = courseQueryVo.getSubjectId();
        Long subjectParentId = courseQueryVo.getSubjectParentId();
        Long teacherId = courseQueryVo.getTeacherId();

        //判断条件值，封装条件
        QueryWrapper<Course> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(title)) {
            wrapper.like("title", title);
        }
        if (!StringUtils.isEmpty(subjectId)) {
            wrapper.eq("subject_id", subjectId);
        }
        if (!StringUtils.isEmpty(subjectParentId)) {
            wrapper.eq("subject_parent_id", subjectParentId);
        }
        if (!StringUtils.isEmpty(teacherId)) {
            wrapper.eq("teacher_id", teacherId);
        }

        //调用方法实现条件查询分页
        Page<Course> pages = baseMapper.selectPage(pageParam, wrapper);
        List<Course> list = pages.getRecords();
        long totalCount = pages.getTotal();
        long totalPage = pages.getPages();
        //封装数据
        Map<String, Object> map = new HashMap<>();
        map.put("totalCount", totalCount);
        map.put("totalPage", totalPage);
        map.put("records", list);
        //处理id

        list.stream().forEach(item -> {
            this.getNameById(item);
        });
        return map;
    }

    private Course getNameById(Course course) {
        Teacher teacher = teacherService.getById(course.getTeacherId());
        if (teacher != null) {
            String name = teacher.getName();
            course.getParam().put("teacherName", name);
        }

        Subject subjectOne = subjectService.getById(course.getSubjectParentId());
        if (subjectOne != null) {
            course.getParam().put("subjectParentTitle", subjectOne.getTitle());
        }
        Subject subjectTwo = subjectService.getById(course.getSubjectId());
        if (subjectTwo != null) {
            course.getParam().put("subjectTitle", subjectTwo.getTitle());
        }
        return course;
    }


}
