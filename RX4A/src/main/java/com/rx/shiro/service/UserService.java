package com.rx.shiro.service;

import com.rx.shiro.entity.User;

public interface UserService {
    User findByUserName(String userName);
}
