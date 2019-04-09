package com.rx.shiro.dao;

import com.rx.shiro.entity.Account;
import com.rx.shiro.entity.User;

import java.util.List;

public interface UserDao {
    Account findByLoginName(String login_name);

    void register(Account _account);

    void registerToT_User(User _user);

    //根据loginname查找到T_User
    User findT_UserBylogin_name(String login_name);

    //根据uid查到所有角色id
    List<String> findRoleIdByUid(String id);

    //根据每一个角色id查到code（按钮）
    List<String> findCodeByRoleId(String roleId);

    //根据角色id查到所有的reource的id
    List<String> findResourceByRoleId(String roleId);

    //根据resource的id查到对应资源的code
    String findReourceCodeByResouId(String resourceId);

}
