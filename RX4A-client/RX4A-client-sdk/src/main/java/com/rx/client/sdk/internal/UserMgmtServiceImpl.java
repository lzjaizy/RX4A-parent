package com.rx.client.sdk.internal;

import com.rx.client.*;
import com.rx.client.sdk.SdkConstants;
import com.rx.client.sdk.UserMgmtService;
import com.rx.client.util.Constants;
import com.rx.client.util.HttpRequests;
import com.rx.client.util.Strings;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.apache.http.HttpStatus;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.BasicCookieStore;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户管理服务.
 *
 */
public class UserMgmtServiceImpl extends BaseApiService implements UserMgmtService {
  private static final Log LOGGER = LogFactory.getLog(UserMgmtServiceImpl.class);
  protected String gatewayBaseUrl;

  /**
   * Constructor with AppContext instance.
   *
   * @param appContext
   */
  public UserMgmtServiceImpl(AppContext appContext) {
    super(appContext);
    gatewayBaseUrl = getConfiguration(SdkConstants.PROP_GATEWAY_SERVER_URL);
  }

  @Override
  public String login(String userName, String password) throws ApiException, ServiceException, IOException {
    if (Strings.isEmpty(userName) || password == null) {
      throw new IllegalArgumentException("Arguments userName or password is missing.");
    }

    Map<String, String> pairs = new HashMap<>();
    pairs.put(Constants.FORM_USER_NAME, userName);
    pairs.put(Constants.FORM_PASSWORD, password);
    pairs.put(Constants.FORM_VERFICATION, 1 + "");
    HttpPost post = HttpRequests.newHttpPost(gatewayBaseUrl + Constants.URL_LOGIN, pairs);
    //post.addHeader(Constants.FORM_TENANTID, getTenantId());
    post.addHeader(Constants.FORM_TENANTID, getConfiguration(SdkConstants.PROP_TENANT_ID));
    //HttpClientContext context = HttpClientContext.create();
    HttpClientContext context = new HttpClientContext();
    CookieStore cookieStore = new BasicCookieStore();
    context.setCookieStore(cookieStore);

    String token = doPost(post,context, new TypeToken<RestApiResponse<String>>() {});

    if (Strings.isEmpty(token)) {
      throw new ServiceException(Constants.CODE_EMPTY_RESULT, "Return an empty token.");
    }

    // Store HttpContext.
    appContext.putIfAbsentHttpContext(token,context);

    return token;
  }

  @Override
  public String getUserId(String userName, String password) throws ApiException, ServiceException, IOException {
    if (Strings.isEmpty(userName) || password == null) {
      throw new IllegalArgumentException("Arguments userName or password is missing.");
    }

    Map<String, String> pairs = new HashMap<>();
    pairs.put(Constants.FORM_USER_NAME, userName);
    pairs.put(Constants.FORM_PASSWORD, password);
    pairs.put(Constants.FORM_VERFICATION, 1 + "");
    HttpPost post = HttpRequests.newHttpPost(gatewayBaseUrl + Constants.URL_LOGIN, pairs);
    //post.addHeader(Constants.FORM_TENANTID, getTenantId());
    post.addHeader(Constants.FORM_TENANTID, getConfiguration(SdkConstants.PROP_TENANT_ID));
    //HttpClientContext context = HttpClientContext.create();
    HttpClientContext context = new HttpClientContext();
    CookieStore cookieStore = new BasicCookieStore();
    context.setCookieStore(cookieStore);
    HttpPost hg = HttpRequests.newHttpPost(gatewayBaseUrl + Constants.URL_LOGIN, pairs);
    ApiResponse<Map<String, String>> response = appContext.getHttpClient().execute(hg,
        new ApiResponseHandler<Map<String,String>>() {});
    if (response.getStatusCode() == HttpStatus.SC_INTERNAL_SERVER_ERROR) {
      throw new ServiceException(response.getStatusCode()+"", "", null);
    }
    if (response.getStatusCode() != HttpStatus.SC_OK) {
      throw new ApiException(response.getStatusCode(),response.getHeaders(),response.getData().get("userId"));
    }

    //Map<String,String> tokenMap = new Json().deserialize(response.getData(), HashMap.class);
    //String token = tokenMap.get(Constants.FORM_TOKEN);
    String userId = response.getData().get("userId");
    if (Strings.isEmpty(userId)) {
      throw new ServiceException(Constants.CODE_EMPTY_RESULT, "Return an empty token.");
    }
    // Store HttpContext.
    appContext.putIfAbsentHttpContext(userId,context);

    return userId;
  }

  @Override
  public void logout(String token) throws ApiException, ServiceException, IOException {
    if (Strings.isEmpty(token)) throw new IllegalArgumentException("Argument token is required.");

    Map<String, String> pairs = new HashMap<>();
    HttpPost post = HttpRequests.newHttpPost(gatewayBaseUrl + Constants.URL_LOGOUT, pairs);
    //post.addHeader(Constants.FORM_TENANTID, getTenantId());
    post.addHeader(Constants.FORM_TENANTID, getConfiguration(SdkConstants.PROP_TENANT_ID));
    post.addHeader(Constants.FORM_TOKEN, token);
    HttpClientContext context = appContext.getHttpContext(token);

    TypeToken<RestApiResponse<String>> typeToken = new TypeToken<RestApiResponse<String>>() {};
    RestApiResponse<String> response = appContext.getHttpClient().execute(post,
        new RestApiResponseHandler<>(typeToken), context);
    if (!response.isSuccess()) {
      LOGGER.warn(String.format("Failed to logout with token - %s", token));
      LOGGER.warn(response.getMessage());
    }

    // remove context.
    appContext.removeContext(token);

    LOGGER.info(String.format("User %s logout.", token));
  }

//  @Override
//  public UserInfo register(String token, UserInfo userInfo) throws IOException {
//    try {
//      HttpPost hp = HttpRequests.newHttpPost2(gatewayBaseUrl + "/api/user", userInfo);
//      hp.setHeader(SdkConstants.FORM_TOKEN, token);
//      LOGGER.debug(String.format("URI -> %s", hp.getURI()));
//      HttpClientContext context = appContext.getHttpContext(token);
//
//      return doPost(hp,context, new TypeToken<RestApiResponse<UserInfo>>() {});
//    } catch (IOException ioe) {
//      LOGGER.error(ioe.getMessage(),ioe);
//      throw ioe;
//    }
//  }

//  public ApiResponse getTime(Map<String,String> params,String token) throws IOException {
//    try {
//      HttpPost hg = HttpRequests.newHttpPost(gatewayBaseUrl + "/api/CA/timeTest", params,true);
//      hg.setHeader("token", token);
//      ApiResponse<String> response = appContext.getHttpClient().execute(hg, new ApiResponseHandler<String>() {});
//
//      LOGGER.debug(String.format("URI -> %s", hg.getURI()));
//      return response;
//    } catch (IOException ioe) {
//      LOGGER.error(ioe);
//      throw ioe;
//    }
//  }

//  public HttpResponse producer(Map<String,String> params) throws IOException {
//    try {
//      HttpPost hg = HttpRequests.newHttpPost(gatewayBaseUrl + "/api/CA/producer", params,true);
//      //ApiResponse<String> response = httpClient.execute(hg, new ApiResponseHandler<String>() {});
//      LOGGER.debug(String.format("URI -> %s", hg.getURI()));
//      return appContext.getHttpClient().execute(hg);
//    } catch (IOException ioe) {
//      LOGGER.error(ioe);
//      throw ioe;
//    }
//  }
//
//  public HttpResponse consumer(Map<String,String> params) throws IOException {
//    try {
//      HttpPost hg = HttpRequests.newHttpPost(gatewayBaseUrl + "/api/CA/consumer", params,true);
//      //ApiResponse<String> response = httpClient.execute(hg, new ApiResponseHandler<String>() {});
//      LOGGER.debug(String.format("URI -> %s", hg.getURI()));
//      return appContext.getHttpClient().execute(hg);
//    } catch (IOException ioe) {
//      LOGGER.error(ioe);
//      throw ioe;
//    }
//  }

  public ApiResponse<Map<String,String>> userPwd(String token,String newPwd) throws IOException {
    Map<String, String> params = new HashMap<>();
    params.put("newPwd",newPwd);
    try {
      HttpPost hg = HttpRequests.newHttpPost(gatewayBaseUrl + "/api/CA/userPwd", params);
      hg.setHeader("token",token);
      LOGGER.debug(String.format("URI -> %s", hg.getURI()));
      return appContext.getHttpClient().execute(hg, new ApiResponseHandler<Map<String,String>>() {
      });
    } catch (IOException ioe) {
      LOGGER.error(ioe);
      throw ioe;
    } catch (Exception e) {
      LOGGER.error(e);
      throw new IOException(e.getMessage(), e.getCause());
    }
  }

  public ApiResponse<Map<String,String>> changePasswordByUsername(String username,String password,String newPassword)
      throws IOException {
    Map<String, String> params = new HashMap<>();
    params.put("username",username);
    params.put("password",password);
    params.put("newPassword",newPassword);
    try {
      HttpPost hg = HttpRequests.newHttpPost(gatewayBaseUrl + "/api/CA/changePasswordByUsername", params);
      LOGGER.debug(String.format("URI -> %s", hg.getURI()));
      return appContext.getHttpClient().execute(hg, new ApiResponseHandler<Map<String,String>>() {
      });
    } catch (IOException ioe) {
      LOGGER.error(ioe);
      throw ioe;
    } catch (Exception e) {
      LOGGER.error(e);
      throw new IOException(e.getMessage(), e.getCause());
    }
  }

  @Override
  public String getGatewayBaseUrl() {
    return gatewayBaseUrl;
  }

  @Override
  public void setGatewayBaseUrl(String gatewayBaseUrl) {
    this.gatewayBaseUrl = gatewayBaseUrl;
  }
}