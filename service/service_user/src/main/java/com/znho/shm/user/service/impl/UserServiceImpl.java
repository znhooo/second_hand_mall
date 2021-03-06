package com.znho.shm.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.znho.shm.common.util.VerCodeGenerateUtil;
import com.znho.shm.model.user.User;
import com.znho.shm.model.vo.UserInfoQueryVo;
import com.znho.shm.user.mapper.UserMapper;
import com.znho.shm.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public IPage<User> selectPage(Page<User> page, UserInfoQueryVo userInfoQueryVo) {
        Long id = userInfoQueryVo.getId();
        Date createTimeBegin = userInfoQueryVo.getCreateTimeBegin();
        Date createTimeEnd = userInfoQueryVo.getCreateTimeEnd();
        String keyword = userInfoQueryVo.getKeyword();
        Integer status = userInfoQueryVo.getStatus();
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        if (createTimeBegin != null)
            userQueryWrapper.ge("create_time",createTimeBegin);
        if (createTimeEnd != null)
            userQueryWrapper.le("create_time",createTimeEnd);
        if (keyword != null)
            userQueryWrapper.like("name",keyword);
        if (id != null)
            userQueryWrapper.eq("id",id);
        if (status != null)
            userQueryWrapper.eq("status",status);
        IPage<User> userIPage = baseMapper.selectPage(page,userQueryWrapper);
        return userIPage;
    }

    @Override
    public String sendEmail(String to) {
        SimpleMailMessage message = new SimpleMailMessage();
        System.out.println(from);

        message.setFrom(from);

        message.setTo(to);

        message.setSubject("????????????????????????");

        String verCode = VerCodeGenerateUtil.generateVerCode();

        message.setText("??????:\n"
                + "\n?????????????????????????????????:" + verCode + ",???????????? 5 ???????????????????????????????????????????????????????????????\n"
                + "\n??????????????????????????????????????????\n(??????????????????????????????????????????????????????????????????");

        javaMailSender.send(message);
        return verCode;
    }
}
