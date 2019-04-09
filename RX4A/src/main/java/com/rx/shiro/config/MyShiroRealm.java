package com.rx.shiro.config;

import com.rx.shiro.entity.Account;
import com.rx.shiro.entity.User;
import com.rx.shiro.service.UserService;
import java.util.List;
import javax.annotation.Resource;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

public class MyShiroRealm extends AuthorizingRealm {

  @Resource
  private UserService userService;

  //权限信息，包括角色以及权限
  @Override
  protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
    System.out.println("权限配置-->MyShiroRealm.doGetAuthorizationInfo()");
    SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

    //添加权限资源   添加的假
    info.addStringPermission("user:add");

    return info;
  }

  /*主要是用来进行身份认证的，也就是说验证用户输入的账号和密码是否正确。*/
  @Override
  protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
      throws AuthenticationException {
    System.out.println("MyShiroRealm.doGetAuthenticationInfo()");
    //获取用户的输入的账号.
    String userName = (String) token.getPrincipal();

    //通过username从数据库中查找 User对象.
    //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
    Account user = userService.findByLoginName(userName);
    System.out.println("==================----->>user=" + user);

    if (user == null) {
      return null;
    }

    //这里进行用户名，密码比对
    SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
        user, //这里传入的是user对象，比对的是用户名，直接传入用户名也没错，但是在授权部分就需要自己重新从数据库里取权限
        user.getPassword(), //密码
        ByteSource.Util.bytes(user.getSalt()),//salt=loginname+salt   getCredentialsSalt()
        getName()  //realm name
    );
    return authenticationInfo;
  }

}