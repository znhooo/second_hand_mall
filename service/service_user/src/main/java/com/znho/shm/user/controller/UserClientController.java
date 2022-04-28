package com.znho.shm.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.znho.shm.common.result.R;
import com.znho.shm.model.user.Address;
import com.znho.shm.model.user.Contact;
import com.znho.shm.model.user.User;
import com.znho.shm.model.vo.UserLoginVo;
import com.znho.shm.user.service.AddressService;
import com.znho.shm.user.service.ContactService;
import com.znho.shm.user.service.FileService;
import com.znho.shm.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@Api(tags = "客户端用户服务")
@RequestMapping("/api/user")
public class UserClientController {

    @Autowired
    private UserService userService;

    @Autowired
    private FileService fileService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private ContactService contactService;

    @PostMapping("login")
    @ApiOperation("登录请求")
    public R login(@RequestBody(required = false) UserLoginVo userLoginVo){
        if (userLoginVo == null){
            return R.fail();
        }
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("phone",userLoginVo.getPhone());
        userQueryWrapper.eq("password",userLoginVo.getPassword());
        User user = userService.getOne(userQueryWrapper);
        if (user != null){
            return R.ok(user);
        }
        else {
            return R.fail();
        }
    }

    @GetMapping("sendEmail/{to}")
    @ApiOperation("邮件验证码")
    public R sendEmail(@PathVariable String to){
        String code = userService.sendEmail(to);
        return R.ok(code);
    }

    @ApiOperation("注册")
    @PostMapping("register")
    public R register(@RequestBody User user){
        if (StringUtils.isEmpty(user.getName())){
            user.setName("user_" + user.getPhone());
        }
        boolean save = userService.save(user);
        Contact contact = new Contact();
        contact.setUserId(user.getId());
        contact.setPhone(user.getPhone());
        contactService.save(contact);
        if (save)
            return R.ok();
        else
            return R.fail();
    }

    @ApiOperation("根据id修改用户信息")
    @PutMapping("")
    public R updateUserById(@RequestBody User user){
        boolean b = userService.updateById(user);
        User byId = userService.getById(user.getId());
        if (b)
            return R.ok(byId);
        else
            return R.fail();
    }

    @ApiOperation("上传头像")
    @PostMapping("uploadAvatar")
    public R uploadAvatar(MultipartFile file,String id) {
        //获取上传文件
        String url = fileService.upload(file);
        User byId = userService.getById(id);
        byId.setAvatar(url);
        userService.updateById(byId);
        return R.ok(url);
    }

    @GetMapping("getAddress/{userId}")
    @ApiOperation("获取用户地址")
    public R getAddress(@PathVariable Long userId){
        QueryWrapper<Address> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",userId);
        List<Address> list = addressService.list(wrapper);
        ArrayList<Address> newList = new ArrayList<>();
        //将默认地址添加到第一个
        for (int i = 0;i < list.size(); i++){
            if (list.get(i).getStatus() == 1){
                newList.add(list.get(i));
                list.remove(i);
                break;
            }
        }
        for (Address address : list){
            newList.add(address);
        }
        return R.ok(newList);
    }

    @ApiOperation("修改地址")
    @PutMapping("updateAddress")
    public R updateAddress(@RequestBody Address address){
        //如果传入默认地址 将之前默认地址取消
        if (address.getStatus() == 1){
            QueryWrapper<Address> wrapper = new QueryWrapper<>();
            wrapper.eq("status",1);
            Address address1 = new Address();
            address1.setStatus(0);
            addressService.update(address1,wrapper);
        }
        boolean b = addressService.updateById(address);
        return b ? R.ok() : R.fail();
    }

    @ApiOperation("添加地址")
    @PostMapping("addAddress")
    public R addAddress(@RequestBody Address address){
        //如果传入默认地址 将之前默认地址取消
        if (address.getStatus() == 1){
            QueryWrapper<Address> wrapper = new QueryWrapper<>();
            wrapper.eq("status",1);
            Address address1 = new Address();
            address1.setStatus(0);
            addressService.update(address1,wrapper);
        }
        boolean b = addressService.save(address);
        return b ? R.ok() : R.fail();
    }

    @ApiOperation("删除地址")
    @DeleteMapping("deleteAddress/{id}")
    public R deleteAddress(@PathVariable Long id){
        boolean b = addressService.removeById(id);
        return b ? R.ok() : R.fail();
    }

    @ApiOperation("根据id获取用户信息,供远程调用")
    @GetMapping("getUserInfoById/{id}")
    public User getUserInfoById(@PathVariable Long id){
        User user = userService.getById(id);
        return user;
    }

    @ApiOperation("获取用户联系卡")
    @GetMapping("getContact/{userId}")
    public R getContact(@PathVariable Long userId){
        QueryWrapper<Contact> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",userId);
        Contact contact = contactService.getOne(wrapper);
        return R.ok(contact);
    }

    @ApiOperation("修改用户联系卡")
    @PutMapping("updateContact")
    public R updateContact(@RequestBody Contact contact){
        boolean b = contactService.updateById(contact);
        return b ? R.ok() : R.fail();
    }

    @ApiOperation("根据地址id获取地址")
    @GetMapping("getAddressById/{id}")
    public R getAddressById(@PathVariable Long id){
        Address address = addressService.getById(id);
        return R.ok(address);
    }

}
