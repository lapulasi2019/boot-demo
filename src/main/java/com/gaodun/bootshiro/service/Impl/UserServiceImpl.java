package com.gaodun.bootshiro.service.Impl;

import com.gaodun.bootshiro.entity.User;
import com.gaodun.bootshiro.mapper.UserMapper;
import com.gaodun.bootshiro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByName(String name) {
        return userMapper.findByName(name);
    }

    @Override
    public User findById(Integer id) {
        return userMapper.findById(id);
    }
}
