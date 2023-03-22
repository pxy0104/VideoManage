package com.pxy.ggkt.vod.service.impl;

import com.alibaba.fastjson.JSON;
import com.pxy.ggkt.vod.service.FileService;
import com.pxy.ggkt.vod.utils.ConstantPropertiesUtil;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

/**
 * @author pxy
 * @software IntelliJ IDEA
 * @create 2023-03-18 14:28
 **/
@Service
public class FileServiceImpl implements FileService {
    @Override
    public String upload(MultipartFile file) {
// 1 初始化用户身份信息（secretId, secretKey）。
        // SECRETID和SECRETKEY请登录访问管理控制台 https://console.cloud.tencent.com/cam/capi 进行查看和管理
        String secretId = ConstantPropertiesUtil.ACCESS_KEY_ID;
        String secretKey = ConstantPropertiesUtil.ACCESS_KEY_SECRET;
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        // 2 设置 bucket 的地域, COS 地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
        // clientConfig 中包含了设置 region, https(默认 http), 超时, 代理等 set 方法, 使用可参见源码或者常见问题 Java SDK 部分。
        Region region = new Region(ConstantPropertiesUtil.END_POINT);
        ClientConfig clientConfig = new ClientConfig(region);
        // 这里建议设置使用 https 协议
        // 从 5.6.54 版本开始，默认使用了 https
        clientConfig.setHttpProtocol(HttpProtocol.https);
        // 3 生成 cos 客户端。
        COSClient cosClient = new COSClient(cred, clientConfig);
        String bucketName = ConstantPropertiesUtil.BUCKET_NAME;
//        String key = file.getOriginalFilename();
        String key = UUID.randomUUID().toString().replaceAll("-", "") +
                file.getOriginalFilename();
        String dateUrl = new DateTime().toString("yyyy/MM/dd");
        key = dateUrl + "/" + key;
        try {
            // 指定要上传的文件
            InputStream inputStream = file.getInputStream();


            ObjectMetadata objectMetadata = new ObjectMetadata();
            PutObjectRequest putObjectRequest =
                    new PutObjectRequest(bucketName, key, inputStream, objectMetadata);
            PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);
//            System.out.println(JSON.toJSONString(putObjectResult));
            //https://ggkt-atguigu-1310644373.cos.ap-beijing.myqcloud.com/01.jpg
            String url = "https://" + bucketName + "." + "cos" + "." + ConstantPropertiesUtil.END_POINT + ".myqcloud.com" + "/" + key;
            return url;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
