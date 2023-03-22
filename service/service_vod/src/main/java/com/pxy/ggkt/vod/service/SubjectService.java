package com.pxy.ggkt.vod.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.pxy.ggkt.model.vod.Subject;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author pxy
 * @since 2023-03-18
 */
public interface SubjectService extends IService<Subject> {

    List<Subject> selectSubjectList(Long id);

    void exportData(HttpServletResponse response);


    //course classification import
    void importData(MultipartFile file);

}
