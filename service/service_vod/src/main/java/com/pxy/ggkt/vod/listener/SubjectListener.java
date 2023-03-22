package com.pxy.ggkt.vod.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.pxy.ggkt.model.vod.Subject;
import com.pxy.ggkt.vo.vod.SubjectEeVo;
import com.pxy.ggkt.vod.mapper.SubjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author pxy
 * @software IntelliJ IDEA
 * @create 2023-03-18 22:30
 **/
@Component
public class SubjectListener extends AnalysisEventListener<SubjectEeVo> {


    @Autowired
    private SubjectMapper subjectMapper;

    //从第二行读取，排除表头
    @Override
    public void invoke(SubjectEeVo subjectEeVo, AnalysisContext context) {
        Subject subject = new Subject();
        BeanUtils.copyProperties(subjectEeVo,subject);
        subjectMapper.insert(subject);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {

    }
}
