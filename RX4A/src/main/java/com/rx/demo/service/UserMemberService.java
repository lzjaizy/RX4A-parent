package com.rx.demo.service;

import com.github.pagehelper.Page;
import com.rx.demo.entity.PageEntity;
import com.rx.demo.entity.User;

public interface UserMemberService {
    public User getUserById(String userId);

    public Page<User> getUsers(PageEntity pageEntity);

    boolean addUser(User record);
}