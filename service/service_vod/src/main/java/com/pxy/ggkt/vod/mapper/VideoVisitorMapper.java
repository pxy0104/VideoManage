package com.pxy.ggkt.vod.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pxy.ggkt.model.vod.VideoVisitor;
import com.pxy.ggkt.vo.vod.VideoVisitorCountVo;
import com.pxy.ggkt.vo.vod.VideoVisitorVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 视频来访者记录表 Mapper 接口
 * </p>
 *
 * @author pxy
 * @since 2023-03-20
 */
public interface VideoVisitorMapper extends BaseMapper<VideoVisitor> {

    //课程统计接口
    List<VideoVisitorCountVo> findCount(@Param("courseId")Long courseId,
                                        @Param("startDate")String startDate,
                                        @Param("endDate")String endDate);

}
