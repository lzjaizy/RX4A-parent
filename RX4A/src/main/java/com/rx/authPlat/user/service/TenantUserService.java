package com.rx.authPlat.user.service;

import com.rx.authPlat.user.entity.Role;
import com.rx.authPlat.user.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TenantUserService {
    User isUser(String login_name);

    List<User> queryUsers(String login_name, String name);

    int addUser(User user);

    List<Role> queryUserRoles(String id);
}
