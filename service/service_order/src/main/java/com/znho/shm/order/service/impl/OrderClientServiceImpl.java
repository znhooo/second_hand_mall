package com.znho.shm.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.znho.shm.good.client.OrderFeignClient;
import com.znho.shm.model.good.Good;
import com.znho.shm.model.order.Order;
import com.znho.shm.order.mapper.OrderMapper;
import com.znho.shm.order.service.OrderClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderClientServiceImpl extends ServiceImpl<OrderMapper,Order>implements OrderClientService {

    @Autowired
    private OrderFeignClient orderFeignClient;

    @Override
    public int createOrder(Long buyUserId, Long sellUserId, Long goodId, Long addressId) {
        //生成订单号
        int r1=(int)(Math.random()*(10));
        int r2=(int)(Math.random()*(10));
        long now = System.currentTimeMillis();
        String orderId =String.valueOf(r1)+ r2 + now;
        //修改商品表的商品状态
        Good good = new Good();
        good.setId(goodId);
        good.setUserId(sellUserId);
        good.setBuyUserId(buyUserId);
        good.setStatus(1);
        orderFeignClient.updateGoodStatus(good);
        //创建订单
        Order order = new Order();
        order.setBuyUserId(buyUserId);
        order.setSellUserId(sellUserId);
        order.setGoodId(goodId);
        order.setOrderId(orderId);
        order.setAddressId(addressId);
        order.setStatus(1);

        return baseMapper.insert(order);
    }
}
