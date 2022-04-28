package com.znho.shm.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.znho.shm.model.user.Contact;
import com.znho.shm.user.mapper.ContactMapper;
import com.znho.shm.user.service.ContactService;
import org.springframework.stereotype.Service;

@Service
public class ContactServiceImpl extends ServiceImpl<ContactMapper,Contact> implements ContactService {
}
