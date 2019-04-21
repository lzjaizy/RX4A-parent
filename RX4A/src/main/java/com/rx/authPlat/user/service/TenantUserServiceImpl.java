package com.rx.authPlat.user.service;

import com.rx.authPlat.user.dao.TenantUserMapper;
import com.rx.authPlat.user.entity.Role;
import com.rx.authPlat.user.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @PackageName com.rx.authPlat.user.service
 * @ClassName UserServiceImpl
 * @Description 用户业务层实现
 * @Author MengQingJun
 * @Date 2019/4/13
 * @Version 2.0
 **/
@Service
public class TenantUserServiceImpl implements TenantUserService {
    @Autowired
    private TenantUserMapper tenantUserMapper;
    @Override
    public User isUser(String login_name) {
        User user= tenantUserMapper.findUserByName(login_name);
        return user;
    }

    @Override
    public List<User> queryUsers(String login_name, String name) {
        List<User> list= tenantUserMapper.queryUsers(login_name,name);
        return list;
    }

    @Override
    public List<Role> queryUserRoles(String id) {
        List<Role> list=tenantUserMapper.queryUserRoles(id);
        return list;
    }

    @Override
    public int addUser(User user) {
        int i=tenantUserMapper.addUser(user);
        return i;
    }


}
