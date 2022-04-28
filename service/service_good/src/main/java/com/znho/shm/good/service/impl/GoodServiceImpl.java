package com.znho.shm.good.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.znho.shm.good.mapper.GoodMapper;
import com.znho.shm.good.service.GoodService;
import com.znho.shm.model.good.Good;
import com.znho.shm.model.vo.GoodQueryVo;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class GoodServiceImpl extends ServiceImpl<GoodMapper, Good> implements GoodService{

    @Override
    public IPage<Good> selectPage(Page<Good> goodPage, GoodQueryVo goodQueryVo) {
        Long id = goodQueryVo.getId();
        Date createTimeBegin = goodQueryVo.getCreateTimeBegin();
        Date createTimeEnd = goodQueryVo.getCreateTimeEnd();
        Long userId = goodQueryVo.getUserId();
        Long buyUserId = goodQueryVo.getBuyUserId();
        String keyword = goodQueryVo.getKeyword();
        Integer status = goodQueryVo.getStatus();
        QueryWrapper<Good> goodQueryWrapper = new QueryWrapper<>();
        if (createTimeBegin != null)
            goodQueryWrapper.ge("create_time",createTimeBegin);
        if (createTimeEnd != null)
            goodQueryWrapper.le("create_time",createTimeEnd);
        if (keyword != null)
            goodQueryWrapper.like("name",keyword);
        if (id != null)
            goodQueryWrapper.eq("id",id);
        if (status != null)
            goodQueryWrapper.eq("status",status);
        if (userId != null)
            goodQueryWrapper.eq("user_id",userId);
        if (buyUserId != null)
            goodQueryWrapper.eq("buy_user_id",buyUserId);
        IPage<Good> goodIPage = baseMapper.selectPage(goodPage, goodQueryWrapper);
        return goodIPage;
    }
}
