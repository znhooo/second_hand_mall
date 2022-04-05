package com.znho.shm.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.znho.shm.model.user.User;
import com.znho.shm.model.vo.UserInfoQueryVo;

public interface UserService extends IService<User> {

    //查询用户列表，带分页
    IPage<User> selectPage(Page<User> page, UserInfoQueryVo userInfoQueryVo);

}
