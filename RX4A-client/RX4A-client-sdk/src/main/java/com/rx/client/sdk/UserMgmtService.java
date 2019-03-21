package com.rx.client.sdk;

import com.rx.client.ApiException;
import com.rx.client.ApiResponse;
import com.rx.client.ApiService;
import com.rx.client.ServiceException;

import java.io.IOException;
import java.util.Map;

/**
 * 用户管理服务.
 *
 */
public interface UserMgmtService extends UserMgmtBaseService {

  /**
   * 获取用户 ID.
   *
   * @param userName 用户名
   * @param password 密码
   * @return 用户 ID
   * @throws IOException
   */
  String getUserId(String userName, String password) throws IOException;

  /**
   * Logout the system.
   *
   * @throws ApiException
   * @throws ServiceException
   * @throws IOException
   */
  void logout(String token) throws IOException;

  /**
   *
   * @param token
   * @param newPwd
   * @return
   * @throws IOException
   */
  ApiResponse<Map<String,String>> userPwd(String token,String newPwd) throws IOException;

  /**
   *
   * @param username
   * @param password
   * @param newPassword
   * @return
   * @throws IOException
   */
  ApiResponse<Map<String,String>> changePasswordByUsername(String username,String password,String newPassword)
      throws IOException;

  String gatewayBaseUrl = null;
  String getGatewayBaseUrl();

  void setGatewayBaseUrl(String gatewayBaseUrl);

}
