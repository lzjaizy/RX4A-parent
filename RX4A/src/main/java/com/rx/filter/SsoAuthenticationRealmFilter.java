package com.rx.filter;

//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import org.apache.shiro.web.filter.authc.UserFilter;
//import org.apache.shiro.web.util.WebUtils;
//
//public class SsoAuthenticationRealmFilter extends UserFilter {
//
//  @Override
//  protected boolean isAccessAllowed(ServletRequest request, ServletResponse response,
//      Object mappedValue) {
//    boolean allowed = super.isAccessAllowed(request, response, mappedValue);
//    if (!allowed) { // 判断请求是否是options请求
//     String method = WebUtils.toHttp(request).getMethod();
//     if (StringUtils.equalsIgnoreCase("OPTIONS", method))
//     { return true; }
//    }
//     return allowed;
//  }
//    }
