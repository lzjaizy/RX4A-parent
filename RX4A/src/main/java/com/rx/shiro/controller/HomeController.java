package com.rx.shiro.controller;

import com.rx.shiro.entity.Account;
import com.rx.shiro.entity.User;
import com.rx.shiro.service.LoginService;
import com.rx.shiro.service.UserService;
import com.rx.utils.LoginResult;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.UUID;

@Controller
public class HomeController {

  @Resource
  private LoginService loginService;

  @Resource
  private UserService userService;

  @RequestMapping({"/", "/index"})
  public String index() {
    return "/index";
  }

  @RequestMapping("/403")
  public String unauthorizedRole() {
    System.out.println("------没有权限-------");
    return "/403";
  }

  @RequestMapping(value = "/login", method = RequestMethod.GET)
  public String toLogin(Map<String, Object> map, HttpServletRequest request) {
    loginService.logout();
    return "/login";
  }

  @RequestMapping(value = "/login", method = RequestMethod.POST)
  public String login(Map<String, Object> map, HttpServletRequest request) throws Exception {
    System.out.println("login()");
    String userName = request.getParameter("username");
    String password = request.getParameter("password");

    LoginResult loginResult = loginService.login(userName, password);
    if (loginResult.isLogin()) {
      return "/index";
    } else {
      map.put("msg", loginResult.getResult());
      map.put("username", userName);
      return "/login";
    }
  }

  /**
   * 写假数据，测试注册功能
   *
   * @Parm registerName
   * @Parm password
   */
  @RequestMapping(value = "/register", method = RequestMethod.POST)
  public String register(HttpServletRequest request) {
    String registerName = request.getParameter("registerName");
    String password = request.getParameter("password");

    //生成盐值
    String salt = new SecureRandomNumberGenerator().nextBytes().toHex();
    //生成的密文
    String ciphertext = new Md5Hash(password, salt, 2).toString();

    Account account = new Account();
    String uuid1 = UUID.randomUUID().toString().replaceAll("-", "");
    account.setId(uuid1);
    account.setName(registerName);
    account.setLogin_name(registerName);
    account.setSalt(salt);
    account.setPassword(ciphertext);

    /**
     * 权限赋予还没写,先赋予角色
     */
    //注册基本信息到T_account表里，
    userService.register(account);
    //以及t_user表里,生成uuid
    String uuid = UUID.randomUUID().toString().replaceAll("-", "");
    User user = new User();
    user.setId(uuid);
    user.setName(registerName);
    user.setLogin_name(registerName);
    userService.registerToT_User(user);

    return "/login";
  }

  @RequestMapping("/logout")
  public String logOut(HttpSession session) {
    loginService.logout();
    return "/login";
  }
}