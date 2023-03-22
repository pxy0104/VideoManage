package com.pxy.ggkt.vod.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pxy.ggkt.model.vod.Video;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author pxy
 * @since 2023-03-19
 */
public interface VideoService extends IService<Video> {
        //根据课程id删除小节
    void removeVideoByCourseId(Long id);

    //删除小节
    void removeVideoById(Long id);


}
