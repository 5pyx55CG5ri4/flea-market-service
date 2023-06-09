package com.fleamarket.modules.user.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fleamarket.modules.user.domain.User;
import com.fleamarket.modules.user.mapper.UserMapper;
import com.fleamarket.modules.user.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
