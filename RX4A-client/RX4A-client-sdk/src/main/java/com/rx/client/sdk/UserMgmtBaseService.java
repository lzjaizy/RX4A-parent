package com.rx.client.sdk;

import com.rx.client.ApiException;
import com.rx.client.ApiService;
//import com.cesgroup.xfc.common.model.UserInfo;

import java.io.IOException;

public interface UserMgmtBaseService extends ApiService {

  /**
   * Login service.
   *
   * @param userName 用户名.
   * @param password 用户密码.
   * @return the access token.
   * @throws ApiException 当服务器返回错误时。
   * @throws IOException
   */
  String login(String userName, String password) throws IOException;

  /**
   * Register user and enroll CA certificate.
   *
   * @param token
   * @param userInfo
   * @return UserInfo object.
   * @throws IOException
   */
//  UserInfo register(String token, UserInfo userInfo) throws IOException;

}
