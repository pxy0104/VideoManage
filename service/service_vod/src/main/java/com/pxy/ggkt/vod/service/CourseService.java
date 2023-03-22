package com.pxy.ggkt.vod.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pxy.ggkt.model.vod.Course;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pxy.ggkt.vo.vod.CourseFormVo;
import com.pxy.ggkt.vo.vod.CoursePublishVo;
import com.pxy.ggkt.vo.vod.CourseQueryVo;

import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author pxy
 * @since 2023-03-19
 */
public interface CourseService extends IService<Course> {

    Map<String, Object> findPageCourse(Page<Course> pageParam, CourseQueryVo courseQueryVo);

    Long saveCourseInfo(CourseFormVo courseFormVo);

    //根据id获取课程信息
    CourseFormVo getCourseInfoById(Long id);

    //修改课程信息
    void updateCourseId(CourseFormVo courseFormVo);

    //根据课程id查询发布的课程信息
    CoursePublishVo getCoursePublishVo(Long id);

    void publishCourse(Long id);

    void removeCourseId(Long id);
}
