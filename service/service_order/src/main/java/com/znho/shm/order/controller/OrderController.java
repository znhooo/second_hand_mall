package com.znho.shm.order.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.znho.shm.common.result.R;
import com.znho.shm.model.order.Order;
import com.znho.shm.model.vo.OrderQueryVo;
import com.znho.shm.order.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/order")
@Api(tags = "订单管理")
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping("{page}/{limit}")
    @ApiOperation("分页查询订单信息")
    public R list(@PathVariable Integer page,
                  @PathVariable Integer limit,
                  OrderQueryVo orderQueryVo){
        Page<Order> orderPage = new Page<>(page, limit);
        IPage<Order> orderIPage = orderService.selectPage(orderPage, orderQueryVo);
        return R.ok(orderIPage);
    }

    @GetMapping("{id}")
    @ApiOperation("根据id查询订单")
    public R selectById(@PathVariable Long id){
        Order byId = orderService.getById(id);
        return R.ok(byId);
    }

    @PutMapping()
    @ApiOperation("根据id修改订单")
    public R insert(@RequestBody Order order){
        boolean b = orderService.updateById(order);
        if (b)
            return R.ok();
        else
            return R.fail();
    }

    @DeleteMapping("{id}")
    @ApiOperation("根据id删除订单")
    public R deleteById(@PathVariable Long id){
        boolean b = orderService.removeById(id);
        if (b)
            return R.ok();
        else
            return R.fail();
    }

    @DeleteMapping("batch")
    @ApiOperation("批量删除订单")
    public R batchDelete(@RequestBody List<Long> idList){
        boolean b = orderService.removeByIds(idList);
        if (b)
            return R.ok();
        else
            return R.fail();
    }
}
