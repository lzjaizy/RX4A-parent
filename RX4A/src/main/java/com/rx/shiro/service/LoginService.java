package com.rx.shiro.service;

import com.rx.utils.LoginResult;

public interface LoginService {
    LoginResult login(String userName, String password);
    void logout();
}