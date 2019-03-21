package com.rx.client.internal;

import com.rx.client.AppContext;
import com.rx.client.BaseApiService;
import com.rx.client.RestApiResponse;
import com.rx.client.RestApiResponseHandler;
import com.rx.client.SystemApiService;
import com.rx.client.util.Constants;
import com.rx.client.util.HttpRequests;
import com.rx.client.util.Strings;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.reflect.TypeToken;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.methods.HttpGet;

public class SystemApiServiceImpl extends BaseApiService implements SystemApiService {
  private static final Log LOGGER = LogFactory.getLog(SystemApiServiceImpl.class);

  public SystemApiServiceImpl(AppContext appContext) {
    super(appContext);
  }

  /**
   * Echo service.
   *
   * @param message
   * @return
   */
  public RestApiResponse<String> echo(String message) throws IOException {
    if ( !Strings.isEmpty(message) && message.length() >= 1024) {
      throw new IllegalArgumentException("Argument message size is too long.");
    }
    Map<String,String> params = new HashMap<>();
    params.put(Constants.MESSAGE, message);
    HttpGet hg = HttpRequests.newHttpGet(getConfiguration(Constants.PROP_GATEWAY_SERVER_URL) + "/echo",params);
    LOGGER.debug(String.format("URI -> %s",hg.getURI()));
    TypeToken<RestApiResponse<String>> typeToken = new TypeToken<RestApiResponse<String>>() {};
    RestApiResponse<String> response = appContext.getHttpClient().execute(hg,
        new RestApiResponseHandler<>(typeToken));
//    if (!response.isSuccess()) {
//      throw new ServiceException(response.getCode(),response.getMessage());
//    }
    return response;
  }
}
