package com.rx.client.sdk;

import com.rx.client.AppContext;
import com.rx.client.SystemApiService;
import com.rx.client.internal.SystemApiServiceImpl;
import com.rx.client.sdk.internal.*;
import com.rx.client.sdk.internal.CommApiServiceImpl;
import com.rx.client.sdk.internal.UniteGatewayServiceImpl;
import com.rx.client.sdk.internal.UserMemberApiServiceImpl;
import com.rx.client.sdk.internal.UserMgmtServiceImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.HttpClient;

public class SdkClient {
  private static final Log LOGGER = LogFactory.getLog(SdkClient.class);
  private static final SdkClient instance = new SdkClient();
  private final AppContext appContext;
  private final SystemApiService systemApiService;
  private final UserMgmtService userMgmtService;
  private final UserMemberApiService userMemberApiService;
  private final WebsoketService websoketService;
  private final CommApiService commApiService;
  private final UniteGatewayService uniteGatewayService;

  /*
   * Private constructor.
   */
  private SdkClient() {
    // build the httpClient object.
    LOGGER.info("Initialize the SDK Client.");
    appContext = new AppContext();
    systemApiService = new SystemApiServiceImpl(appContext);
    userMgmtService = new UserMgmtServiceImpl(appContext);
    userMemberApiService =new UserMemberApiServiceImpl(appContext);
    websoketService = new WebsoketService(appContext);
    uniteGatewayService = new UniteGatewayServiceImpl(appContext);
    commApiService = new CommApiServiceImpl(appContext);
  }

  /** Do setup. - */
  public void start() {
    // do nothing for now.
  }

  /** Do cleanup. - close the httpClient - clean up the context */
  public void close() {
    LOGGER.info("Close and cleanup the client resources.");
    appContext.close();
  }

  /**
   * Get the instance of the SdkClient singleton object.
   *
   * @return the instance of SdkClient.
   */
  public static SdkClient getInstance() {
    return instance;
  }

  public HttpClient getHttpClient() {
    return appContext.getHttpClient();
  }

  public SystemApiService getSystemApiService() {
    return systemApiService;
  }

  public UserMgmtService getUserMgmtService() {
    return userMgmtService;
  }

  public UserMemberApiService getUserMemberApiService() {
    return userMemberApiService;
  }

  public WebsoketService getWebsoketService() {
    return websoketService;
  }

  public CommApiService getCommApiService() {
    return commApiService;
  }

  public UniteGatewayService getUniteGatewayService() {
    return uniteGatewayService;
  }
  
  
}
