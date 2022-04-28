package com.znho.shm.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.znho.shm.model.order.Order;

public interface OrderClientService extends IService<Order> {
    int createOrder(Long buyUserId,Long SellUserId,Long goodId,Long addressId);
}
