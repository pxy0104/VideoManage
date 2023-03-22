package com.pxy.excel;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author pxy
 * @software IntelliJ IDEA
 * @create 2023-03-18 17:38
 **/

public class TestWrite {
    public static void main(String[] args) {
        //设置文件名称和路径
        String fileName = "C:\\Users\\pxy\\Desktop\\资料\\项目\\gg课" +
                "堂\\1.xlsx";
        EasyExcel.write(fileName,User.class)
                .sheet("testWrite")
                .doWrite(data());
    }

    //循环设置要添加的数据，最终封装到list集合中
    private static List<User> data() {
        List<User> list = new ArrayList<User>();
        for (int i = 0; i < 10; i++) {
            User data = new User();
            data.setId(i);
            data.setName("张三"+i);
            list.add(data);
        }
        return list;
    }
}
