package com.rx.authPlat.tenant.dao;

import com.rx.authPlat.tenant.entity.User;

import java.util.List;

public interface TenantUserMapper {

    User findUserByName(String login_name);

    List<User> queryUsers(String login_name, String name);
}
