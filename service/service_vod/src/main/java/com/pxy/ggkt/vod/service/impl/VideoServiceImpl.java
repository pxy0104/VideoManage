package com.pxy.ggkt.vod.service.impl;

import com.alibaba.excel.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.pxy.ggkt.model.vod.Video;
import com.pxy.ggkt.vod.mapper.VideoMapper;
import com.pxy.ggkt.vod.service.VideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pxy.ggkt.vod.service.VodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author pxy
 * @since 2023-03-19
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService {

    @Autowired
    private VodService vodService;
    //删除小节，同时删除小节里面的视频
    @Override
    public void removeVideoById(Long id) {
//1 删除小节中的视频
        //1 删除视频
        Video video = baseMapper.selectById(id);
        //获取视频id
        String videoSourceId = video.getVideoSourceId();
        //如果视频id不为空，调用方法删除
        if(!StringUtils.isEmpty(videoSourceId)) {
            vodService.removeVideo(videoSourceId);
        }
        //2 删除小节
        baseMapper.deleteById(id);

    }


    //根据课程id删除小节 删除所有的小节视频
    @Override
    public void removeVideoByCourseId(Long id) {
        //1 删除小节中的视频
        //根据课程id获取课程里面所有小节
        QueryWrapper<Video> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",id);
        List<Video> videoList = baseMapper.selectList(wrapper);
        //遍历获取每个小节中的视频id
        for(Video video:videoList) {
            String videoSourceId = video.getVideoSourceId();
            //如果视频id不为空，调用方法删除
            if(!StringUtils.isEmpty(videoSourceId)) {
                vodService.removeVideo(videoSourceId);
            }
        }
        //2 根据课程id删除小节
        baseMapper.delete(wrapper);
    }

}
