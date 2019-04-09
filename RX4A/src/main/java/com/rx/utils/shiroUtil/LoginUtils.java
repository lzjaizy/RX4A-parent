package com.rx.utils.shiroUtil;


import com.rx.shiro.entity.SsoToken;
import java.util.Date;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.codec.Base64;

public class LoginUtils {
  private LoginUtils() {
  }

  public static void login(AuthenticationToken token) {
    SecurityUtils.getSubject().login(token);
  }

  public static void login(String tokenStr) {
    SsoAuthenticationToken token = new SsoAuthenticationToken(tokenStr);
    SecurityUtils.getSubject().login(token);
  }

  public static void login(String tokenStr, boolean isRememberMe) {
    SsoAuthenticationToken token = new SsoAuthenticationToken(tokenStr);
    token.setRememberMe(isRememberMe);
    SecurityUtils.getSubject().login(token);
  }

  public static void logout() {
    SecurityUtils.getSubject().logout();
  }

  public static String generatorToken(String id, String loginName, String userName, long seconds) {
    SsoToken ssoToken = new SsoToken();
    ssoToken.setTokenId(id);
    ssoToken.setLoginName(loginName);
    ssoToken.setUserName(userName);
    ssoToken.setExpireDate(new Date((new Date()).getTime() + seconds * 1000L));
    String mytoken = SsoTokenUtil.encodeToken(ssoToken);
    return new String(Base64.encode(mytoken.getBytes()));
  }
}

