package com.znho.shm.good.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.znho.shm.good.mapper.GoodMapper;
import com.znho.shm.good.service.GoodClientService;
import com.znho.shm.model.good.Good;
import com.znho.shm.model.user.User;
import com.znho.shm.model.vo.GoodListVo;
import com.znho.shm.model.vo.GoodQueryVo;
import com.znho.shm.user.client.GoodFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GoodClientServiceImpl extends ServiceImpl<GoodMapper, Good> implements GoodClientService {

    @Autowired
    GoodFeignClient goodFeignClient;

//    private List<GoodListVo> getGoodList(Page<Good> goodPage,GoodQueryVo goodQueryVo) {
//
//    }

    @Override
    public Map<String,Object> selectPage(Page<Good> goodPage, GoodQueryVo goodQueryVo) {

        Long id = goodQueryVo.getId();
        Date createTimeBegin = goodQueryVo.getCreateTimeBegin();
        Date createTimeEnd = goodQueryVo.getCreateTimeEnd();
        Long userId = goodQueryVo.getUserId();
        Long buyUserId = goodQueryVo.getBuyUserId();
        String keyword = goodQueryVo.getKeyword();
        Boolean timeSort = goodQueryVo.getTimeSort();
        Boolean priceDescSort = goodQueryVo.getPriceDescSort();
        Boolean priceAscSort = goodQueryVo.getPriceAscSort();

        QueryWrapper<Good> wrapper = new QueryWrapper<>();
        if (createTimeBegin != null)
            wrapper.ge("create_time",createTimeBegin);
        if (createTimeEnd != null)
            wrapper.le("create_time",createTimeEnd);
        if (keyword != null)
            wrapper.like("name",keyword);
        if (id != null)
            wrapper.eq("id",id);
        if (userId != null)
            wrapper.eq("user_id",userId);
        if (buyUserId != null)
            wrapper.eq("buy_user_id",buyUserId);
        if (timeSort != null && timeSort){
            wrapper.orderByDesc("create_time");
        }
        if (priceDescSort != null && priceDescSort){
            wrapper.orderByDesc("price");
        }
        if (priceAscSort != null && priceAscSort){
            wrapper.orderByAsc("price");
        }

        wrapper.eq("status",4);
        IPage<Good> iPage = baseMapper.selectPage(goodPage,wrapper);
        List<Good> goods = iPage.getRecords();

        ArrayList<GoodListVo> goodListVos = new ArrayList<>();
        for (Good good : goods) {
            User userInfoById = goodFeignClient.getUserInfoById(good.getUserId());
            GoodListVo goodListVo = new GoodListVo();
            goodListVo.setAvatar(userInfoById.getAvatar());
            goodListVo.setCreateTime(good.getCreateTime());
            goodListVo.setDescription(good.getDescription());
            goodListVo.setGoodId(good.getId());
            goodListVo.setGoodImg(good.getImg());
            goodListVo.setGoodName(good.getName());
            goodListVo.setPrice(good.getPrice());
            goodListVo.setSellUserId(good.getUserId());
            goodListVo.setSite(good.getSite());
            goodListVo.setUserName(userInfoById.getName());
            goodListVo.setLatitude(good.getLatitude());
            goodListVo.setLongitude(good.getLongitude());
            goodListVo.setStatus(good.getStatus());
            goodListVos.add(goodListVo);
        }

        HashMap<String, Object> map = new HashMap<>();
        map.put("data",goodListVos);
        map.put("current",iPage.getCurrent());
        map.put("pages",iPage.getPages());
        map.put("size",iPage.getSize());
        map.put("total",iPage.getTotal());
        map.put("hasNext",goodPage.hasNext());
        map.put("hasPrevious",goodPage.hasPrevious());
        return map;

    }

    @Override
    public List<GoodListVo> getByUserId(Long id,String column) {
        QueryWrapper<Good> wrapper = new QueryWrapper<>();
        wrapper.eq(column,id);
        wrapper.orderByDesc("update_time");
        List<Good> goods = baseMapper.selectList(wrapper);
        ArrayList<GoodListVo> goodListVos = new ArrayList<>();
        for (Good good : goods) {
            User userInfoById = goodFeignClient.getUserInfoById(good.getUserId());
            GoodListVo goodListVo = new GoodListVo();
            goodListVo.setAvatar(userInfoById.getAvatar());
            goodListVo.setCreateTime(good.getCreateTime());
            goodListVo.setDescription(good.getDescription());
            goodListVo.setGoodId(good.getId());
            goodListVo.setGoodImg(good.getImg());
            goodListVo.setGoodName(good.getName());
            goodListVo.setPrice(good.getPrice());
            goodListVo.setSellUserId(good.getUserId());
            goodListVo.setSite(good.getSite());
            goodListVo.setUserName(userInfoById.getName());
            goodListVo.setUpdateTime(good.getUpdateTime());
            goodListVo.setLatitude(good.getLatitude());
            goodListVo.setLongitude(good.getLongitude());
            goodListVo.setStatus(good.getStatus());
            goodListVo.setBuyUserId(good.getBuyUserId());
            goodListVos.add(goodListVo);
        }
        return goodListVos;

    }
}
