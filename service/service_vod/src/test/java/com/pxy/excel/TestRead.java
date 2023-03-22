package com.pxy.excel;

import com.alibaba.excel.EasyExcel;

/**
 * @author pxy
 * @software IntelliJ IDEA
 * @create 2023-03-18 19:49
 **/

public class TestRead {
    public static void main(String[] args) {
        String fileName = "C:\\Users\\pxy\\Desktop\\资料\\项目\\gg课" +
                "堂\\1.xlsx";
        EasyExcel.read(fileName,User.class,new ExcelListener()).sheet().doRead();
    }
}
