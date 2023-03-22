package com.pxy.ggkt.vod.controller;

import com.pxy.ggkt.result.Result;
import com.pxy.ggkt.vod.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author pxy
 * @software IntelliJ IDEA
 * @create 2023-03-18 14:27
 **/

@Api(tags = "file upload interface") //总的注解
@RestController
@RequestMapping("/admin/vod/file")
public class FileUploadController {

    @Autowired
    private FileService fileService;

    @ApiOperation("file upload")
    @PostMapping("upload")
    public Result uploadFile(MultipartFile file) {
        String url = fileService.upload(file);
        return Result.ok(url).message("file upload seccess.");
    }

}
