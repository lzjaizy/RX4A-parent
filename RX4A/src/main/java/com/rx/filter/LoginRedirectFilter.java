package com.rx.filter;

import com.alibaba.fastjson.JSONObject;
import com.rx.utils.shiroUtil.CesBeanUtils;
import com.rx.utils.shiroUtil.LoginUtils;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.http.MediaType;

public class LoginRedirectFilter extends FormAuthenticationFilter {

  @Override
  protected boolean onAccessDenied(ServletRequest request, ServletResponse response)
      throws Exception {
    // 在这里进行验证码的校验
    System.out.println("1111111");
    return super.onAccessDenied(request, response);
  }

  @Override
  protected boolean onLoginSuccess(
      AuthenticationToken token, Subject subject,
      ServletRequest request, ServletResponse response) throws Exception {
      //第一次登录成功，获取用户信息,生成token
    String userName = (String) token.getPrincipal();
//    String password =(String) token.getCredentials();

      //生成ssotoken,假数据
    String ssotoken = LoginUtils.generatorToken("74565a41e68b4ae9a4e385cd62b79e91",userName,userName,60000000L);
      //创建session
    HttpSession session = WebUtils.toHttp(request).getSession();
    session.setAttribute("loginName", userName);
    //将角色放到session中
//    String userId = ((Account) LoginSecurityUtil.getUser().getUserDetail()).getId();
    Map resultMap = new HashMap();
    resultMap.put("success","true");
    resultMap.put("data",ssotoken);
    resultMap.put("userId","1122334455");
    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    response.setCharacterEncoding("UTF-8");
    response.getWriter().write(CesBeanUtils.toJson(resultMap));


    System.out.println("lzj++++++++++++++++++++onLoginSuccess");
    return false;
  }

  @Override
  protected boolean onLoginFailure(AuthenticationToken token,
      AuthenticationException authenticationException,
      ServletRequest request, ServletResponse response) {
    System.out.println("lzj++++++++++++++++++++onLoginFailure");
    return true;
  }
}

