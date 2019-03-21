package com.rx.client.sdk;

import java.io.IOException;
import java.util.Map;

public interface UniteGatewayService {
   
  public Map<String, String> endorse(String token, String signProposalString, Map<String,String> map) throws IOException;
  
  public Map<String, String> sendOrderer(String token, String envelopeString, String txId) throws Exception;
}
