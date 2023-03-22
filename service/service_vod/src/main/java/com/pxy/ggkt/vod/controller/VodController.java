package com.pxy.ggkt.vod.controller;

import com.pxy.ggkt.exception.GgktException;
import com.pxy.ggkt.result.Result;
import com.pxy.ggkt.vod.service.VodService;
import com.pxy.ggkt.vod.utils.ConstantPropertiesUtil;
import com.pxy.ggkt.vod.utils.Signature;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

/**
 * @author pxy
 * @software IntelliJ IDEA
 * @create 2023-03-20 22:24
 **/

@Api(tags = "tencent vod：video on demand")
@RestController
@RequestMapping(value = "/admin/vod/")
//@CrossOrigin
public class VodController {

    @Autowired
    private VodService vodService;

    //返回客户端上传视频签名
    @GetMapping("sign")
    public Result sign(){
        Signature sign = new Signature();
        // 设置 App 的云 API 密钥
        sign.setSecretId(ConstantPropertiesUtil.ACCESS_KEY_ID);
        sign.setSecretKey(ConstantPropertiesUtil.ACCESS_KEY_SECRET);
        sign.setCurrentTime(System.currentTimeMillis() / 1000);
        sign.setRandom(new Random().nextInt(java.lang.Integer.MAX_VALUE));
        sign.setSignValidDuration(3600 * 24 * 2); // 签名有效期：2天
        try {
            String signature = sign.getUploadSignature();
            System.out.println("signature : " + signature);
            return Result.ok(signature);
        } catch (Exception e) {
            System.out.print("获取签名失败");
            e.printStackTrace();
           throw new GgktException(20001,"获取签名失败");
        }
    }

    //上传视频接口
    @PostMapping("upload")
    public Result upload() {

        String fileId = vodService.uploadVideo();
        return Result.ok(fileId);
    }

    //删除视频接口
    @DeleteMapping("remove/{fileId}")
    public Result remove(@PathVariable String fileId){
        vodService.removeVideo(fileId);
        return Result.ok(null);

    }
}
