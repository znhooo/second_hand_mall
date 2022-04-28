package com.znho.shm.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.znho.shm.model.user.Address;
import com.znho.shm.user.mapper.AddressMapper;
import com.znho.shm.user.service.AddressService;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements AddressService {
}
