package com.znho.shm.good.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.znho.shm.model.good.Good;
import com.znho.shm.model.vo.GoodListVo;
import com.znho.shm.model.vo.GoodQueryVo;

import java.util.List;
import java.util.Map;

public interface GoodClientService extends IService<Good> {

    Map<String,Object> selectPage(Page<Good> goodPage, GoodQueryVo goodQueryVo);

    List<GoodListVo> getByUserId(Long id,String column);

}
