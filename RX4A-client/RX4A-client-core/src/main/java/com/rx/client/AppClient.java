package com.rx.client;

import com.rx.client.internal.SystemApiServiceImpl;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.LaxRedirectStrategy;

/**
 * AppClient 是整个客户端的入口. 其职责在于： - 创建和维护的 HttpClient 对象； - 为每个登录用户创建和管理 HttpContext 对象； -
 * 创建和获取必要的客户端服务；
 *
 * @author yang.tiedang@cesgroup.com.cn
 * @since 1.0
 */
public class AppClient {
  private static final Log LOGGER = LogFactory.getLog(AppClient.class);
  private static AppClient instance = new AppClient();
  private final AppContext appContext;
  private final SystemApiService systemApiService;
  //private final UserMgmtService userMgmtService;
  //private final AdminUserMgmtService adminUserMgmtService;
  //private final ExplorerApiService explorerApiService;
//  private final OutStockApiService outStockApiService;
//  private final InStockApiService inStockApiService;
//
//
//
//  private final CheckApiService checkApiService;

  /*
   * Private constructor.
   */
  private AppClient() {
    // build the httpClient object.
    HttpClient httpClient = HttpClientBuilder.create().setRedirectStrategy(new LaxRedirectStrategy()).build();
    Protocol.registerProtocol("https",
        new Protocol("https", new MySSLProtocolSocketFactory(), 443));
    appContext = new AppContext(httpClient);
    systemApiService = new SystemApiServiceImpl(appContext);
    //userMgmtService = new UserMgmtService(httpClient);
    //adminUserMgmtService = new AdminUserMgmtService(httpClient);
    //explorerApiService = new ExplorerApiService(httpClient);
//    outStockApiService = new OutStockApiService(httpClient);
//    inStockApiService = new InStockApiService(httpClient);
//    checkApiService=new CheckApiService(httpClient);
  }

  /** Do setup. - */
  public void start() {
    // do nothing for now.
  }

  /** Do cleanup. - close the httpClient - clean up the context */
  public void close() {
    appContext.close();
  }

  /**
   * Get the instance of the AppClient singleton object.
   *
   * @return the instance of AppClient.
   */
  public static AppClient getInstance() {
    return instance;
  }

  /**
   * Get the initialized HttpClient object.
   *
   * @return the httpClient object.
   */
  public HttpClient getHttpClient() {
    return appContext.getHttpClient();
  }

  public SystemApiService getSystemApiService() {
    return systemApiService;
  }

//  public UserMgmtService getUserMgmtService() {
//    return userMgmtService;
//  }

//  public AdminUserMgmtService getAdminUserMgmtService() {
//    return adminUserMgmtService;
//  }

//
//  public ExplorerApiService getExplorerApiService() {
//    return explorerApiService;
//  }

//  public OutStockApiService getOutStockApiService() {
//    return outStockApiService;
//  }
//
//  public InStockApiService getInStockApiService() {
//    return inStockApiService;
//  }
//  public CheckApiService getCheckApiService() {
//    return checkApiService;
//  }
}
