package com.pxy.ggkt.vod.service;

import com.pxy.ggkt.model.vod.VideoVisitor;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 视频来访者记录表 服务类
 * </p>
 *
 * @author pxy
 * @since 2023-03-20
 */
public interface VideoVisitorService extends IService<VideoVisitor> {
    //课程统计接口
    Map<String, Object> findCount(Long courseId, String startDate, String endDate);

//    Map<String, Object> findCount(Long courseId, String startDate, String endDate);

}
