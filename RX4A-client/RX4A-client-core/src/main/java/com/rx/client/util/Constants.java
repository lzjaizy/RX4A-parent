package com.rx.client.util;

public interface Constants {
  String MESSAGE = "message";
  String APP_KEY = "aapKey";

  String PROP_GATEWAY_SERVER_URL = "gateway.server.url";
  String PROP_WS_SERVER_URL = "ws.server.url";
  String PROP_EXPLORER_SERVER_URL = "explorer.server.url";
  //String PROP_EXPLORER_FABRIC_URL = "explorer.fabric.url";
  String PROP_COIN_SERVER_URL = "coin.server.url";
  String PROP_TENANT_ID = "tenant.id";
  String PROP_TENANT_ADMIN = "tenant.admin";
  String PROP_TENANT_ADMIN_PASSWORD = "tenant.admin.password";
  String FORM_USER_NAME = "username";
  String FORM_PASSWORD = "password";
  String FORM_TENANTID = "tenantId";
  String FORM_VERFICATION = "verification";
  String FORM_TOKEN = "token";

  String HEADER_ACCEPT = "Accept";
  String HEADER_CONTENT_TYPE = "Content-type";
  String HEADER_USER_AGENT = "User-Agent";
  String HEADER_COOKIE = "Cookie";

  String HEADER_USER_AGENT_MOZILLA = "Mozilla/5.0";

  String URL_LOGIN = "/login.html";
  String URL_LOGOUT = "/logout";
  String URL_ECHO = "/echo";
  String URL_HEALTH = "/health";

  String CODE_UNKNOWN = "-1";
  String CODE_SUCCESS = "1";
  String CODE_ERROR = "0";
  String CODE_INVALID_RESULT = "100";
  String CODE_EMPTY_RESULT = "101";
}
