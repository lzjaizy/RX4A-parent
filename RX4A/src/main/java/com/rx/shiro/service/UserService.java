package com.rx.shiro.service;

import com.rx.shiro.entity.Account;
import com.rx.shiro.entity.User;
import java.util.List;

public interface UserService {

  Account findByLoginName(String login_name);

  void register(Account _account);

  void registerToT_User(User _user);

  User findT_UserBylogin_name(String login_name);

  List<String> findRoleIdByUid(String id);

  List<String> findCodeByRoleId(String roleId);

  List<String> findResourceByRoleId(String roleId);

  String findReourceCodeByResouId(String resourceId);
}
