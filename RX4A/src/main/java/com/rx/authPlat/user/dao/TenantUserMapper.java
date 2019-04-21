package com.rx.authPlat.user.dao;

import com.rx.authPlat.user.entity.Role;
import com.rx.authPlat.user.entity.User;

import java.util.List;

public interface TenantUserMapper {

    User findUserByName(String login_name);

    List<User> queryUsers(String login_name, String name);

    int addUser(User user);

    List<Role> queryUserRoles(String id);
}
