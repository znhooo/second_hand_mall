package com.znho.shm.good.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.znho.shm.common.result.R;
import com.znho.shm.good.service.GoodService;
import com.znho.shm.model.good.Good;
import com.znho.shm.model.vo.GoodQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/good")
@Api(tags = "商品管理")
public class GoodController {

    @Autowired
    GoodService goodService;

    @GetMapping("{page}/{limit}")
    @ApiOperation("分页查询商品信息")
    public R list(@PathVariable Integer page,
                  @PathVariable Integer limit,
                  GoodQueryVo goodQueryVo){
        Page<Good> goodPage = new Page<>(page, limit);
        IPage<Good> goodIPage = goodService.selectPage(goodPage, goodQueryVo);
        return R.ok(goodIPage);
    }

    @GetMapping("{id}")
    @ApiOperation("根据id查询商品")
    public R selectById(@PathVariable Long id){
        Good byId = goodService.getById(id);
        return R.ok(byId);
    }

    @PutMapping()
    @ApiOperation("根据id修改商品")
    public R insert(@RequestBody Good good){
        boolean b = goodService.updateById(good);
        if (b)
            return R.ok();
        else
            return R.fail();
    }

    @DeleteMapping("{id}")
    @ApiOperation("根据id删除商品")
    public R deleteById(@PathVariable Long id){
        boolean b = goodService.removeById(id);
        if (b)
            return R.ok();
        else
            return R.fail();
    }

    @DeleteMapping("batch")
    @ApiOperation("批量删除商品")
    public R batchDelete(@RequestBody List<Long> idList){
        boolean b = goodService.removeByIds(idList);
        if (b)
            return R.ok();
        else
            return R.fail();
    }
}
