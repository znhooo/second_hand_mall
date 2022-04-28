package com.znho.shm.good.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.znho.shm.good.service.FileService;
import com.znho.shm.good.utils.ConstantOssPropertiesUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Service
public class FileServiceImpl implements FileService {
    @Override
    public Map<String,List<String>> upload(MultipartFile[] files) {
        String endpoint = ConstantOssPropertiesUtils.EDNPOINT;
        String accessKeyId = ConstantOssPropertiesUtils.ACCESS_KEY_ID;
        String accessKeySecret = ConstantOssPropertiesUtils.SECRECT;
        String bucketName = ConstantOssPropertiesUtils.BUCKET;
        try {
            // 创建OSSClient实例。
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
            // 上传文件流。
            ArrayList<String> urls = new ArrayList<>();
            ArrayList<String> indexs = new ArrayList<>();
            for (MultipartFile file : files){
                InputStream inputStream = file.getInputStream();
                indexs.add(file.getName());
                String fileName = file.getOriginalFilename();
                //生成随机唯一值，使用uuid，添加到文件名称里面
                String uuid = UUID.randomUUID().toString().replaceAll("-","");
                fileName = uuid+fileName;

                //按照当前日期，创建文件夹，上传到创建文件夹里面
                String timeUrl = new DateTime().toString("yyyy/MM/dd");
                fileName = timeUrl+"/"+fileName;

                //调用方法实现上传
                ossClient.putObject(bucketName, fileName, inputStream);
                String url = "https://"+bucketName+"."+endpoint+"/"+fileName;
                urls.add(url);
            }

            // 关闭OSSClient。
            ossClient.shutdown();

            HashMap<String, List<String>> map = new HashMap<>();
            map.put("indexs",indexs);
            map.put("urls",urls);

            //返回
            return map;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
