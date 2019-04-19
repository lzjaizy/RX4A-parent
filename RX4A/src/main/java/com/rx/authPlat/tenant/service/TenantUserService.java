package com.rx.authPlat.tenant.service;

import com.rx.authPlat.tenant.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TenantUserService {
    User isUser(String login_name);

    List<User> queryUsers(String login_name, String name);
}
