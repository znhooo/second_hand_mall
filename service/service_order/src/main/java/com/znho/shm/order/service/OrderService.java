package com.znho.shm.order.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.znho.shm.model.order.Order;
import com.znho.shm.model.vo.OrderQueryVo;

public interface OrderService extends IService<Order> {
    IPage<Order> selectPage(Page<Order> orderPage, OrderQueryVo orderQueryVo);
}
