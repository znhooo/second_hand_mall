package com.znho.shm.user.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.znho.shm.common.result.R;
import com.znho.shm.model.user.User;
import com.znho.shm.model.vo.UserInfoQueryVo;
import com.znho.shm.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/user")
@Api(tags = "用户管理")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation("查询用户列表")
    @GetMapping("{page}/{limit}")
    public R list(@PathVariable Long page,
                  @PathVariable Long limit,
                  UserInfoQueryVo userInfoQueryVo){
        Page<User> userPage = new Page<>(page, limit);
        IPage<User> userIPage = userService.selectPage(userPage, userInfoQueryVo);
        return R.ok(userIPage);
    }

    @ApiOperation("根据id查询用户")
    @GetMapping("{id}")
    public R selectUserById(@PathVariable Long id){
        User byId = userService.getById(id);
        return R.ok(byId);
    }

    @ApiOperation("根据id删除用户")
    @DeleteMapping("{id}")
    public R deleteUserById(@PathVariable Integer id){
        boolean b = userService.removeById(id);
        if (b)
            return R.ok();
        else
            return R.fail();
    }

    @ApiOperation("添加用户")
    @PostMapping()
    public R addUser(@RequestBody User user){
        boolean save = userService.save(user);
        if (save)
            return R.ok();
        else
            return R.fail();
    }

    @ApiOperation("根据id修改用户")
    @PutMapping("")
    public R updateUserById(@RequestBody User user){
        boolean b = userService.updateById(user);
        if (b)
            return R.ok();
        else
            return R.fail();
    }

    @ApiOperation("批量删除用户")
    @DeleteMapping("batch")
    public R batchDeleteUser(@RequestBody List<Long> idList){
        userService.removeByIds(idList);
        return R.ok();

    }
}
