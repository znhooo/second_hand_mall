package com.znho.shm.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.znho.shm.model.order.Order;
import com.znho.shm.model.vo.OrderQueryVo;
import com.znho.shm.order.mapper.OrderMapper;
import com.znho.shm.order.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Override
    public IPage<Order> selectPage(Page<Order> orderPage, OrderQueryVo orderQueryVo) {
        Long id = orderQueryVo.getId();
        Date createTimeBegin = orderQueryVo.getCreateTimeBegin();
        Date createTimeEnd = orderQueryVo.getCreateTimeEnd();
        Long orderId = orderQueryVo.getOrderId();
        Long sellUserId = orderQueryVo.getSellUserId();
        Long buyUserId = orderQueryVo.getBuyUserId();
        Integer status = orderQueryVo.getStatus();
        Long goodId = orderQueryVo.getGoodId();
        QueryWrapper<Order> orderQueryWrapper = new QueryWrapper<>();
        if (createTimeBegin != null)
            orderQueryWrapper.ge("create_time",createTimeBegin);
        if (createTimeEnd != null)
            orderQueryWrapper.le("create_time",createTimeEnd);
        if (orderId != null)
            orderQueryWrapper.like("order_id",orderId);
        if (id != null)
            orderQueryWrapper.eq("id",id);
        if (status != null)
            orderQueryWrapper.eq("status",status);
        if (sellUserId != null)
            orderQueryWrapper.eq("sell_user_id",sellUserId);
        if (buyUserId != null)
            orderQueryWrapper.eq("buy_user_id",buyUserId);
        if (buyUserId != null)
            orderQueryWrapper.eq("good_id",goodId);
        IPage<Order> orderPage1 = baseMapper.selectPage(orderPage, orderQueryWrapper);
        return orderPage1;
    }
}
