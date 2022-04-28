package com.znho.shm.good.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface FileService {
    //上传文件到阿里云oss
    Map<String,List<String>> upload(MultipartFile[] files);
}
