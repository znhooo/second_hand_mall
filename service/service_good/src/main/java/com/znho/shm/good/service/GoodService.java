package com.znho.shm.good.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.znho.shm.model.good.Good;
import com.znho.shm.model.vo.GoodQueryVo;

public interface GoodService extends IService<Good> {
    IPage<Good> selectPage(Page<Good> goodPage, GoodQueryVo goodQueryVo);
}
