package com.rx.client.sdk.internal;

import com.rx.client.*;
import com.rx.client.sdk.UserMemberApiService;
import com.rx.client.sdk.SdkConstants;
import com.rx.client.util.Constants;
import com.rx.client.util.HttpRequests;
import com.rx.client.util.Strings;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.BasicCookieStore;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class UserMemberApiServiceImpl extends BaseApiService implements UserMemberApiService {
  private static final Log LOG = LogFactory.getLog(UserMemberApiServiceImpl.class);
  private String gatewayBaseUrl;

  public UserMemberApiServiceImpl(AppContext appContext) {
    super(appContext);
    gatewayBaseUrl = getConfiguration(SdkConstants.PROP_GATEWAY_SERVER_URL);
  }

  /**
   * 新增或更新用户证书信息。
   *
   * @return 成功
   */
  public Map<String, Object> showUser(String id) throws IOException {
    Map<String,String> params = new HashMap<>();
    params.put("id", id);

    HttpGet hp = HttpRequests.newHttpGet(gatewayBaseUrl + "/user/showUser", params);
//    hp.setHeader("token", token);
    LOG.debug(String.format("URI -> %s", hp.getURI()));
//    HttpClientContext context = appContext.getHttpContext(token);

    ApiResponse<Map<String,Object>> response = appContext.getHttpClient().execute(hp,
        new ApiResponseHandler<Map<String,Object>>() {});

    return response.getData();
  }

  public List<Map<String, Object>> searchPersonList(int page , int rows) throws IOException {
    Map<String,Object> params = new HashMap<>();
    params.put("page", page);
    params.put("rows",rows);
    Properties props = this.getConfiguration();
    String channelName = props.getProperty("channel");
//    params.put("channelName", channelName);

    HttpPost hp = HttpRequests.newHttpPost2(gatewayBaseUrl + "/user/searchPersonList", params,false);
    LOG.debug(String.format("URI -> %s", hp.getURI()));
//    HttpClientContext context = appContext.getHttpContext(token);

    ApiResponse<Map<String,Object>> response = appContext.getHttpClient().execute(hp,
        new ApiResponseHandler<Map<String,Object>>() {});

    return (List<Map<String, Object>>)response.getData().get("rows");

  }

  private String login(String userName, String password) throws IOException {
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
    HttpClientContext context = new HttpClientContext();
    CookieStore cookieStore = new BasicCookieStore();
    context.setCookieStore(cookieStore);

    RestApiResponse<String> response = appContext.getHttpClient().execute(post,
        new RestApiResponseHandler<>(
            new TypeToken<RestApiResponse<String>>() {}), context);

    if (!response.isSuccess()) {
      throw new ServiceException(response.getCode(),response.getMessage());
    }

    String token = response.getData();

    if (Strings.isEmpty(token)) {
      throw new ServiceException(Constants.CODE_EMPTY_RESULT, "Return an empty token.");
    }

    // Store HttpContext.
    appContext.putIfAbsentHttpContext(token,context);

    return token;
  }
}
