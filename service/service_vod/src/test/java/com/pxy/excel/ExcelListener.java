package com.pxy.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.pxy.excel.User;
import java.util.Map;

/**
 * @author pxy
 * @software IntelliJ IDEA
 * @create 2023-03-18 19:37
 **/

public class ExcelListener extends AnalysisEventListener<User> {
    //一行一行读取excel内容，把每行内容封装到user对象
    //从第二行开始读取，排除表头内容
    @Override
    public void invoke(User user, AnalysisContext analysisContext) {
        System.out.println(user);
    }

    // Read header content
    @Override
    public void invokeHeadMap(Map headMap, AnalysisContext context) {
        System.out.println("table header:" + headMap);
    }


    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
