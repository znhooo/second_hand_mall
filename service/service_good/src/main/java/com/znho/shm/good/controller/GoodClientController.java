package com.znho.shm.good.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.znho.shm.common.result.R;
import com.znho.shm.good.service.FileService;
import com.znho.shm.good.service.GoodClientService;
import com.znho.shm.good.service.GoodImgService;
import com.znho.shm.model.good.Good;
import com.znho.shm.model.good.GoodImg;
import com.znho.shm.model.vo.GoodListVo;
import com.znho.shm.model.vo.GoodQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/good")
@Api(tags = "商品服务")
public class GoodClientController {

    @Autowired
    private GoodClientService goodClientService;

    @Autowired
    private FileService fileService;

    @Autowired
    private GoodImgService goodImgService;

    @GetMapping("{page}/{limit}")
    @ApiOperation("分页获取商品列表")
    public R list(@PathVariable Integer page, @PathVariable Integer limit,
                  GoodQueryVo goodQueryVo){
        Page<Good> goodPage = new Page<>(page, limit);
        Map<String, Object> map = goodClientService.selectPage(goodPage, goodQueryVo);
        return R.ok(map);
    }

    @GetMapping("getSellGoods/{id}")
    @ApiOperation("获取发布的商品信息")
    public R getSellGoods(@PathVariable Long id){
        List<GoodListVo> goodListVos = goodClientService.getByUserId(id,"user_id");
        return R.ok(goodListVos);
    }

    @GetMapping("getBuyGoods/{id}")
    @ApiOperation("获取买到的商品信息")
    public R getBuyGoods(@PathVariable Long id){
        List<GoodListVo> goodListVos = goodClientService.getByUserId(id,"buy_user_id");
        return R.ok(goodListVos);
    }

    @GetMapping("getBuyGoodsCount/{id}")
    @ApiOperation("获取买到的商品数量")
    public R getBuyGoodsCount(@PathVariable Long id){
        QueryWrapper<Good> wrapper = new QueryWrapper<>();
        wrapper.eq("buy_user_id",id);
        int count = goodClientService.count(wrapper);
        return R.ok(count);
    }

    @GetMapping("getSellGoodsCount/{id}")
    @ApiOperation("获取卖出的商品数量")
    public R getSellGoodsCount(@PathVariable Long id){
        QueryWrapper<Good> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",id);
        int count = goodClientService.count(wrapper);
        return R.ok(count);
    }

    @ApiOperation("发布商品")
    @PostMapping("releaseGood")
    public R releaseGood(HttpServletRequest httpRequest, Good good){
        MultipartHttpServletRequest req = (MultipartHttpServletRequest) httpRequest;
        Map<String, MultipartFile> fileMap = req.getFileMap();
        MultipartFile[] files = new MultipartFile[fileMap.size()];
        int j = 0;
        for (MultipartFile file : fileMap.values()){
            files[j] = file;
            j++;
        }
        Map<String, List<String>> map = fileService.upload(files);
        List<String> urls = map.get("urls");
        List<String> indexs = map.get("indexs");
        good.setImg(urls.get(0));
        boolean save = goodClientService.save(good);
        int i = 0;
        for (String url : urls){
            GoodImg goodImg = new GoodImg();
            goodImg.setGoodId(good.getId());
            goodImg.setImg(url);
            goodImg.setFlag(indexs.get(i));
            goodImgService.save(goodImg);
            i++;
        }
        return save ? R.ok() : R.fail();
    }

    @ApiOperation("修改商品")
    @PostMapping("updateGood")
    public R updateGood(HttpServletRequest httpRequest, Good good){
        MultipartHttpServletRequest req = (MultipartHttpServletRequest) httpRequest;
        Map<String, MultipartFile> fileMap = req.getFileMap();
        if (!fileMap.isEmpty()){
            MultipartFile[] files = new MultipartFile[fileMap.size()];
            int j = 0;
            for (MultipartFile file : fileMap.values()){
                files[j] = file;
                j++;
            }
            Map<String, List<String>> map = fileService.upload(files);
            List<String> urls = map.get("urls");
            List<String> indexs = map.get("indexs");

            //保存修改的图片
            QueryWrapper<GoodImg> wrapper = new QueryWrapper<>();
            wrapper.eq("good_id",good.getId());
            int i = 0;
            for (String index : indexs){
                wrapper.eq("flag",index);
                GoodImg one = goodImgService.getOne(wrapper);
                if (one != null){
                    one.setImg(urls.get(i));
                    goodImgService.updateById(one);
                }else {
                    GoodImg goodImg = new GoodImg();
                    goodImg.setGoodId(good.getId());
                    goodImg.setImg(urls.get(i));
                    goodImg.setFlag(index);
                    goodImgService.save(goodImg);
                }
                i++;
            }


        }

        boolean update = goodClientService.updateById(good);
        return update ? R.ok() : R.fail();
    }

    @ApiOperation("删除商品")
    @DeleteMapping("deleteGood/{id}")
    public R deleteGood(@PathVariable Long id){
        boolean delete = goodClientService.removeById(id);
        return delete ? R.ok() : R.fail();
    }

    @ApiOperation("创建订单时更新商品状态，供远程调用")
    @RequestMapping("updateGoodStatus")
    public boolean updateGoodStatus(@RequestBody Good good){
        return goodClientService.updateById(good);
    }

    @ApiOperation("获取商品图片列表")
    @GetMapping("getGoodImgs/{goodId}")
    public R getGoodImgs(@PathVariable Long goodId){
        QueryWrapper<GoodImg> wrapper = new QueryWrapper<>();
        wrapper.eq("good_id",goodId);
        List<GoodImg> list = goodImgService.list(wrapper);
        return R.ok(list);
    }
}
