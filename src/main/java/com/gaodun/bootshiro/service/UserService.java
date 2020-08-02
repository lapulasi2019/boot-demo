package com.gaodun.bootshiro.service;

import com.gaodun.bootshiro.entity.User;

public interface UserService {

    User findByName(String name);
}
