package com.rx.client.sdk.internal;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.rx.client.sdk.UniteGatewayService;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;
import com.rx.client.AppContext;
import com.rx.client.BaseApiService;
import com.rx.client.RestApiResponse;
import com.rx.client.ServiceException;
import com.rx.client.sdk.SdkConstants;
import com.rx.client.util.HttpRequests;
import com.rx.client.util.Strings;
import com.google.gson.reflect.TypeToken;

public class UniteGatewayServiceImpl extends BaseApiService implements UniteGatewayService {
  
  private static final Log LOG = LogFactory.getLog(UniteGatewayServiceImpl.class);
  
  private String gatewayBaseUrl;
  
  public UniteGatewayServiceImpl(AppContext appContext) {
    super(appContext);
    gatewayBaseUrl = getConfiguration(SdkConstants.PROP_GATEWAY_SERVER_URL);
    // TODO Auto-generated constructor stub
  }

  @Override
  public Map<String, String> endorse(String token, String signProposalString, Map<String, String> map) throws IOException {
    // TODO Auto-generated method stub
    if (Strings.isEmpty(token)) {
      throw new IllegalArgumentException("Arguments token and outStockDetail are required.");
    }
    Map<String, Object> params = new HashMap<>();


    String channelName = map.get("channelName");
    String chainCodeName = map.get("chainCodeName");
    params.put(SdkConstants.FORM_CHANNEL_NAME, channelName);
    params.put(SdkConstants.FORM_CHAINCODE_NAME, chainCodeName);
    params.put(SdkConstants.PROPOSAL_STRING, signProposalString);
    HttpPost hp = HttpRequests.newHttpPost2(gatewayBaseUrl + "/api/endorse", params, true);
    hp.setHeader(SdkConstants.FORM_TOKEN, token);
    LOG.debug(String.format("URI -> %s", hp.getURI()));
    HttpClientContext context = appContext.getHttpContext(token);
    Map<String, String> resMap = new HashMap<>();
    try{
      Map<String, String> result = doPost(hp, context, new TypeToken<RestApiResponse<Map<String, String>>>() {});
      resMap.put("success", "true");
      resMap.put("payloadString", result.get("payloadString"));
      resMap.put(SdkConstants.TX_ID, result.get("txId"));
      resMap.put("message", null);
    } catch (ServiceException e) {
      resMap.put("success", "false");
      resMap.put("message", e.getMessage());
      resMap.put("payloadString", null);
      resMap.put(SdkConstants.TX_ID, null);
    }
    return resMap;
  }

  @Override
  public Map<String, String> sendOrderer(String token, String envelopeString, String txId) throws Exception {
    // TODO Auto-generated method stub

    if (Strings.isEmpty(token)) {
      throw new IllegalArgumentException("Arguments token and outStockDetail are required.");
    }
    
    Map<String, String> params = new HashMap<>();
    params.put(SdkConstants.ENVELOPE_STRING, envelopeString);
    params.put("txId", txId);
    HttpPost hp = HttpRequests.newHttpPost(gatewayBaseUrl + "/api/sendOrderer", params, true);
    hp.setHeader(SdkConstants.FORM_TOKEN, token);
    LOG.debug(String.format("URI -> %s", hp.getURI()));
    HttpClientContext context = appContext.getHttpContext(token);
    
    return doPost(hp, context, new TypeToken<RestApiResponse<Map<String, String>>>() {});

  }

}
