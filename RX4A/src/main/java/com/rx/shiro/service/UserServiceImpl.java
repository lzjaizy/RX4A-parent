package com.rx.shiro.service;

import com.rx.shiro.dao.UserDao;
import com.rx.shiro.entity.Account;
import com.rx.shiro.entity.User;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  @Resource
  private UserDao userDao;

  @Override
  public Account findByLoginName(String login_name) {
    return userDao.findByLoginName(login_name);
  }

  @Override
  public void register(Account _account) {
    userDao.register(_account);
  }

  @Override
  public void registerToT_User(User _user) {
    userDao.registerToT_User(_user);
  }

  @Override
  public User findT_UserBylogin_name(String login_name) {
    return userDao.findT_UserBylogin_name(login_name);
  }

  @Override
  public List<String> findRoleIdByUid(String id) {
    return userDao.findRoleIdByUid(id);
  }

  @Override
  public List<String> findCodeByRoleId(String roleId) {
    return userDao.findCodeByRoleId(roleId);
  }

  @Override
  public List<String> findResourceByRoleId(String roleId) {
    return userDao.findResourceByRoleId(roleId);
  }

  @Override
  public String findReourceCodeByResouId(String resourceId) {
    return userDao.findReourceCodeByResouId(resourceId);
  }
}