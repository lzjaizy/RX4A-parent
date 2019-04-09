package com.rx.utils.shiroUtil;

import org.apache.shiro.authc.RememberMeAuthenticationToken;

public class SsoAuthenticationToken implements RememberMeAuthenticationToken {
  private static final long serialVersionUID = -2329959419582633851L;
  private String token;
  private String principal;
  private boolean rememberMe = false;

  public SsoAuthenticationToken(String token) {
    this.token = token;
  }

  public Object getPrincipal() {
    return this.principal;
  }

  public Object getCredentials() {
    return this.token;
  }

  public String getToken() {
    return this.token;
  }

  public void setPrincipal(String principal) {
    this.principal = principal;
  }

  public boolean isRememberMe() {
    return this.rememberMe;
  }

  public void setRememberMe(boolean rememberMe) {
    this.rememberMe = rememberMe;
  }
}