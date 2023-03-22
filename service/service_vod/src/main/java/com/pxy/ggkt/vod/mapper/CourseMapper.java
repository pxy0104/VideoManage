package com.pxy.ggkt.vod.mapper;

import com.pxy.ggkt.model.vod.Course;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pxy.ggkt.vo.vod.CoursePublishVo;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author pxy
 * @since 2023-03-19
 */
public interface CourseMapper extends BaseMapper<Course> {

    CoursePublishVo selectCoursePublishVoById(Long id);

}
