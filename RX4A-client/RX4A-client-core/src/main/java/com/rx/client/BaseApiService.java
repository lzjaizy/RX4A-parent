package com.rx.client;

import com.rx.client.util.ConfigUtils;
import com.rx.client.util.Constants;
import java.io.IOException;
import java.util.Properties;

import com.rx.client.util.HttpRequests;
import com.rx.client.util.Strings;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;

/**
 * 客户端 HTTP 服务基础类.
 *
 * @author yang.tiedang@cesgroup.com.cn
 * @since 1.0
 */
public class BaseApiService {
  private static final Log LOGGER = LogFactory.getLog(BaseApiService.class);

  protected AppContext appContext;
  protected Properties configuration;


  public BaseApiService(AppContext appContext) {
    this.appContext = appContext;
    try {
      configuration = ConfigUtils.fromResourcePath(getClass(), "/password-client.properties");
    } catch (IOException ioe) {
      LOGGER.warn("Failed to load config file", ioe);
    }
  }

  public void setAppContext(AppContext appContext) {
    this.appContext = appContext;
  }

  public AppContext getAppContext() {
    return appContext;
  }

  public Properties getConfiguration() {
    return this.configuration;
  }

  /**
   * Get configuration value by key.
   *
   * @param key
   * @return
   */
  public String getConfiguration(String key) {
    if (this.configuration != null) {
      return configuration.getProperty(key);
    }
    return null;
  }

//  public void setTenantId(String tenantId) {
//    this.tenantId = tenantId;
//  }
//
//  public String getTenantId() {
//    return tenantId;
//  }
//
//  public String getGatewayBaseUrl() {
//    return gatewayBaseUrl;
//  }
//
//  public void setGatewayBaseUrl(String gatewayBaseUrl) {
//    this.gatewayBaseUrl = gatewayBaseUrl;
//  }
//
//  public String getExplorerBaseUrl() {
//    return explorerBaseUrl;
//  }
//
//  public void setExplorerBaseUrl(String explorerBaseUrl) {
//    this.explorerBaseUrl = explorerBaseUrl;
//  }

  /**
   *
   * @param post
   * @param clientContext
   * @param typeToken
   * @param <T>
   * @return
   * @throws IOException
   */
  protected <T> T doPost(HttpPost post, HttpClientContext clientContext, TypeToken<RestApiResponse<T>> typeToken)
      throws IOException {
    RestApiResponse<T> response = appContext.getHttpClient().execute(post,
        new RestApiResponseHandler<>(typeToken), clientContext);

    if (!response.isSuccess()) {
      throw new ServiceException(response.getCode(), response.getMessage());
    }

    if (response.getStatusCode() != HttpStatus.SC_OK) {
      throw new ApiException(response.getStatusCode(),response.getHeaders(),"");
    }

    return response.getData();
  }

  /**
   *
   * @param get
   * @param clientContext
   * @param typeToken
   * @param <T>
   * @return
   * @throws IOException
   */
  protected <T> T doGet(HttpGet get, HttpClientContext clientContext, TypeToken<RestApiResponse<T>> typeToken) throws IOException{
    RestApiResponse<T> response = appContext.getHttpClient().execute(get,
        new RestApiResponseHandler<>(typeToken), clientContext);

    if (!response.isSuccess()) {
      throw new ServiceException(response.getCode(), response.getMessage());
    }

    if (response.getStatusCode() != HttpStatus.SC_OK) {
      throw new ApiException(response.getStatusCode(),response.getHeaders(),"");
    }

    return response.getData();
  }

  /**
   *
   * @param delete
   * @param clientContext
   * @param typeToken
   * @param <T>
   * @return
   * @throws IOException
   */
  protected <T> T doDelete(HttpDelete delete, HttpClientContext clientContext, TypeToken<RestApiResponse<T>> typeToken) throws IOException{
    RestApiResponse<T> response = appContext.getHttpClient().execute(delete,
        new RestApiResponseHandler<>(typeToken), clientContext);

    if (!response.isSuccess()) {
      throw new ServiceException(response.getCode(), response.getMessage());
    }

    if (response.getStatusCode() != HttpStatus.SC_OK) {
      throw new ApiException(response.getStatusCode(),response.getHeaders(),"");
    }

    return response.getData();
  }

  /**
   *
   * @param token
   * @param url
   * @param id
   * @return
   * @throws IOException
   */
  protected String doDeleteById(String token, String url, String id) throws IOException {
    if (Strings.isEmpty(token) || Strings.isEmpty(url) || Strings.isEmpty(id)) {
      throw new IllegalArgumentException("Arguments token, url and id are required.");
    }

    HttpDelete hd = HttpRequests.newHttpDelete(url, id);
    hd.setHeader(Constants.FORM_TOKEN, token);
    LOGGER.debug(String.format("URI -> %s", hd.getURI()));
    HttpClientContext context = appContext.getHttpContext(token);

    return doDelete(hd, context, new TypeToken<RestApiResponse<String>>() {});
  }

  /**
   *
   * @param token
   * @param baseUrl
   * @param id
   * @param typeToken
   * @param <T>
   * @return
   * @throws IOException
   */
  protected <T> T doGetById(String token, String baseUrl, String id, TypeToken<RestApiResponse<T>> typeToken) throws IOException {
    if ( Strings.isEmpty(token) || Strings.isEmpty(baseUrl) || Strings.isEmpty(id) || typeToken == null) {
      throw new IllegalArgumentException("Arguments token, baseUrl, id and typeToken are required.");
    }

    // set params.
    HttpGet hg = HttpRequests.newHttpGet(baseUrl + "/" + id, null);
    hg.setHeader(Constants.FORM_TOKEN, token);
    LOGGER.debug(String.format("URI -> %s", hg.getURI()));
    HttpClientContext context = appContext.getHttpContext(token);

    return doGet(hg, context, typeToken);
  }
}
