package com.gaodun.bootshiro.mapper;

import com.gaodun.bootshiro.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    User findByName(String name);

    User findById(Integer id);
}
