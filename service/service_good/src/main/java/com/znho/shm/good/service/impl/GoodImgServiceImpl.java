package com.znho.shm.good.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.znho.shm.good.mapper.GoodImgMapper;
import com.znho.shm.good.service.GoodImgService;
import com.znho.shm.model.good.GoodImg;
import org.springframework.stereotype.Service;

@Service
public class GoodImgServiceImpl extends ServiceImpl<GoodImgMapper, GoodImg> implements GoodImgService {
}
