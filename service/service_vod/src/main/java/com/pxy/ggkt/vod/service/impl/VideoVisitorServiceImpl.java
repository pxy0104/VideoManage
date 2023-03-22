package com.pxy.ggkt.vod.service.impl;

import com.pxy.ggkt.model.vod.VideoVisitor;
import com.pxy.ggkt.vo.vod.VideoVisitorCountVo;
import com.pxy.ggkt.vod.mapper.VideoVisitorMapper;
import com.pxy.ggkt.vod.service.VideoVisitorService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * <p>
 * 视频来访者记录表 服务实现类
 * </p>
 *
 * @author pxy
 * @since 2023-03-20
 */
@Service
public class VideoVisitorServiceImpl extends ServiceImpl<VideoVisitorMapper, VideoVisitor> implements VideoVisitorService {

    //课程统计接口
    @Override
    public Map<String, Object> findCount(Long courseId, String startDate, String endDate) {
        //调用mapper 条件查询
        List<VideoVisitorCountVo> videoVisitorVoList = baseMapper.findCount(courseId,startDate,endDate);
        HashMap<String, Object> map = new HashMap<>();

        //一个x轴list ，一个y轴list
//        List<String> dateList = new ArrayList<>();
//        List<Integer> countList = new ArrayList<>();
        //封装数据  代表所有日期
        List<String> dateList =
                videoVisitorVoList.stream().map(VideoVisitorCountVo::getJoinTime).
                        collect(Collectors.toList());
        //代表日期对应数量
        List<Integer> countList = videoVisitorVoList.stream().map(VideoVisitorCountVo::getUserCount)
                .collect(Collectors.toList());
        map.put("xData",dateList);
        map.put("yData",countList);
        return map;
    }
}
