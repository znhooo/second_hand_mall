package com.znho.shm.order.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.znho.shm.common.result.R;
import com.znho.shm.good.client.OrderFeignClient;
import com.znho.shm.model.good.Good;
import com.znho.shm.model.order.Order;
import com.znho.shm.order.service.OrderClientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/order")
@Api(tags = "客户端订单服务")
public class OrderClientController {

    @Autowired
    private OrderClientService orderClientService;

    @Autowired
    private OrderFeignClient orderFeignClient;
    
    @ApiOperation("提交订单")
    @PostMapping("commitOrder")
    public R commitOrder(Long buyUserId, Long sellUserId, Long goodId,Long addressId){
        System.out.println(addressId+"--------------");
        int order = orderClientService.createOrder(buyUserId, sellUserId, goodId, addressId);
        return order != 0 ? R.ok() : R.fail();
    }

    @ApiOperation("获取订单详情")
    @GetMapping("getOrder/{goodId}")
    public R getOrder(@PathVariable Long goodId){
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.eq("good_id",goodId);
        Order order = orderClientService.getOne(wrapper);
        return R.ok(order);
    }

    @ApiOperation("更新订单")
    @PutMapping("updateOrder")
    public R updateOrder(@RequestBody Order order){
        if (order.getStatus() == 2){
            order.setDispatchTime(new Date());
        }
        boolean b = orderClientService.updateById(order);
        Good good = new Good();
        good.setId(order.getGoodId());
        good.setStatus(order.getStatus());
        boolean b1 = orderFeignClient.updateGoodStatus(good);
        return b&&b1 ? R.ok() : R.fail();
    }

}
