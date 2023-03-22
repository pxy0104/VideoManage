package com.pxy.ggkt.vod.service;

import com.pxy.ggkt.model.vod.Chapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pxy.ggkt.vo.vod.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author pxy
 * @since 2023-03-19
 */
public interface ChapterService extends IService<Chapter> {

    List<ChapterVo> getTreeList(Long courseId);

    void removeChapterByCourseId(Long id);
}
