package com.rx.client.sdk.internal;

import com.rx.client.*;
import com.rx.client.sdk.CommApiService;
import com.rx.client.sdk.SdkConstants;
import com.rx.client.util.HttpRequests;
import com.rx.client.util.Strings;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommApiServiceImpl extends BaseApiService implements CommApiService {
  private static final Log LOG = LogFactory.getLog(CommApiServiceImpl.class);
  private String gatewayBaseUrl;

  public CommApiServiceImpl(AppContext appContext) {
    super(appContext);
    gatewayBaseUrl = getConfiguration(SdkConstants.PROP_GATEWAY_SERVER_URL);
  }

  @Override
  public Map<String,String> invoke(String token, List<String> list, Map<String, String> map) throws IOException {
    if (Strings.isEmpty(token) || list == null) {
      throw new IllegalArgumentException("Arguments token and outStockDetail are required.");
    }

    Map<String, Object> params = new HashMap<>();
    params.put(SdkConstants.FORM_PARAMS, list);

    String channelName = map.get("channelName");
    String chainCodeName = map.get("chainCodeName");
    String addMethod = map.get("addMethod");

    params.put(SdkConstants.FORM_CHANNEL_NAME, channelName);
    params.put(SdkConstants.FORM_CHAINCODE_NAME, chainCodeName);
    params.put(SdkConstants.FORM_ADD_METHOD, addMethod);

    HttpPost hp = HttpRequests.newHttpPost2(gatewayBaseUrl + "/api/comm", params, true);
    hp.setHeader(SdkConstants.FORM_TOKEN, token);
    LOG.debug(String.format("URI -> %s", hp.getURI()));
    HttpClientContext context = appContext.getHttpContext(token);
    Map<String, String> resMap = new HashMap<>();
    try {
      String transactionID = doPost(hp, context, new TypeToken<RestApiResponse<String>>() {
      });
      resMap.put("code", "success");
      resMap.put("transactionID", transactionID);
      resMap.put("message", null);
    } catch (ServiceException e) {
      resMap.put("code", "error");
      resMap.put("message", e.getMessage());
      resMap.put("transactionID", null);
    }
    return resMap;
  }

  @Override
  public String delete(String token, String id) throws IOException {
    return null;
  }


  @Override
  public List<String> findHistoryById(String token, String id, boolean flag, Map<String, String> map) throws IOException {
    if (Strings.isEmpty(token) || Strings.isEmpty(id)) {
      throw new IllegalArgumentException("Arguments token, and id are required.");
    }
    Map<String, String> params = new HashMap<>();
    params.put(SdkConstants.FORM_FLAG, flag + "");

    String channelName = map.get("channel");
    String chainCodeName = map.get("chainCodeName");
    String addMethod = map.get("addMethod");

    if (Strings.isEmpty(channelName)) {
      throw new ApiException("Property channelName is required.");
    }

    HttpGet hg = HttpRequests
        .newHttpGet(gatewayBaseUrl + "/api/comm/history/" + channelName + "/" + id +"?chainCodeName=" + chainCodeName + "&addMethod=" + addMethod, params);
    hg.setHeader(SdkConstants.FORM_TOKEN, token);
    LOG.debug(String.format("URI -> %s", hg.getURI()));
    HttpClientContext context = appContext.getHttpContext(token);

    return doGet(hg,context,new TypeToken<RestApiResponse<List<String>>>() {});
  }
}
