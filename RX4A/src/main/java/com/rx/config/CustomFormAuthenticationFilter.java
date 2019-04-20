package com.rx.config;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
 
public class CustomFormAuthenticationFilter extends FormAuthenticationFilter{
	  @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
      // 在这里进行验证码的校验
      System.out.println("1111111");
      return super.onAccessDenied(request, response);
    }

    @Override
    protected boolean onLoginSuccess(
        AuthenticationToken token, Subject subject,
        ServletRequest request, ServletResponse response) throws Exception {
        System.out.println("lzj++++++++++++++++++++onLoginSuccess");
        return true;
    }

    @Override
    protected boolean onLoginFailure(AuthenticationToken token,
                                     AuthenticationException authenticationException,
                                     ServletRequest request, ServletResponse response) {
        System.out.println("lzj++++++++++++++++++++onLoginFailure");
        return false;
    }
}
