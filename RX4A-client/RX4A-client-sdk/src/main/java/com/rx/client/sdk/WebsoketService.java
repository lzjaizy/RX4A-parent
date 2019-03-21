package com.rx.client.sdk;

import com.rx.client.AppContext;
import com.rx.client.BaseApiService;
import com.rx.client.websoket.WSClient;

public class WebsoketService extends BaseApiService {

  /**
   * Constructor with httpClient and baseUrl.
   *
   * @param appContext
   *
   */
  public WebsoketService(AppContext appContext) {
    super(appContext);
  }

  /**
   * Retrieve WSClient instance by token.
   *
   * @param token the user login token.
   * @return user WSClient instance.
   * @throws Exception
   */
  public WSClient connWSService(String token) throws Exception {
    return WSClient.getInstance(token);
  }

}
