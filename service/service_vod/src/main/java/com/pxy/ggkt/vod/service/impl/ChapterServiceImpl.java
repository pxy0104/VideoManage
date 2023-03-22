package com.pxy.ggkt.vod.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.pxy.ggkt.model.vod.Chapter;
import com.pxy.ggkt.model.vod.Video;
import com.pxy.ggkt.vo.vod.ChapterVo;
import com.pxy.ggkt.vo.vod.VideoVo;
import com.pxy.ggkt.vod.mapper.ChapterMapper;
import com.pxy.ggkt.vod.service.ChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pxy.ggkt.vod.service.VideoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author pxy
 * @since 2023-03-19
 */
@Service
public class ChapterServiceImpl extends ServiceImpl<ChapterMapper, Chapter> implements ChapterService {

    @Autowired
    private VideoService videoService;

    @Override
    public List<ChapterVo> getTreeList(Long courseId) {
        //根据课程id获取所有的章节
        ArrayList<ChapterVo> finalChapterList = new ArrayList<>();
        QueryWrapper<Chapter> wrapperChapter = new QueryWrapper<>();
        wrapperChapter.eq("course_id", courseId);
        List<Chapter> chapterList = baseMapper.selectList(wrapperChapter);


        //根据课程id获取课程里面所有小节
        LambdaQueryWrapper<Video> wrapperVideo = new LambdaQueryWrapper<>();
        wrapperVideo.eq(Video::getCourseId, courseId);
        List<Video> videoList = videoService.list(wrapperVideo);

        //封装章节
        for (int i = 0; i < chapterList.size(); i++) {
            //得到每个章节
            Chapter chapter = chapterList.get(i);
            //放入到最终的集合中
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(chapter, chapterVo);
            finalChapterList.add(chapterVo);

            //封装章节里面小节
            List<VideoVo> videoVoList = new ArrayList<>();
            for (Video video :
                    videoList) {
                if (chapter.getId().equals(video.getChapterId())) {

                    VideoVo videoVo = new VideoVo();
                    BeanUtils.copyProperties(video, videoVo);
                    videoVoList.add(videoVo);
                }
            }
            chapterVo.setChildren(videoVoList);
        }
        return finalChapterList;
    }

    @Override
    public void removeChapterByCourseId(Long id) {
        QueryWrapper<Chapter> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",id);
        baseMapper.delete(wrapper);
    }
}
