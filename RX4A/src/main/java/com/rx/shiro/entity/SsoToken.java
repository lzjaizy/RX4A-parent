package com.rx.shiro.entity;

import java.util.Date;

public class SsoToken {
  public static final String SSO_TOKEN = "cesssotoken";
  public static final String SSO_TOKEN_PARAMETER = "cesssotoken";
  private String tokenId;
  private String loginName;
  private String loginAppId;
  private String userName;
  private Date expireDate;
  private boolean validToken;

  public SsoToken() {
  }

  public Date getExpireDate() {
    return this.expireDate;
  }

  public void setExpireDate(Date expireDate) {
    this.expireDate = expireDate;
  }

  public String getLoginAppId() {
    return this.loginAppId;
  }

  public void setLoginAppId(String loginAppId) {
    this.loginAppId = loginAppId;
  }

  public String getLoginName() {
    return this.loginName;
  }

  public void setLoginName(String loginName) {
    this.loginName = loginName;
  }

  public String getTokenId() {
    return this.tokenId;
  }

  public void setTokenId(String tokenId) {
    this.tokenId = tokenId;
  }

  public String getUserName() {
    return this.userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public boolean isValidToken() {
    return this.validToken;
  }

  public void setValidToken(boolean validToken) {
    this.validToken = validToken;
  }
}