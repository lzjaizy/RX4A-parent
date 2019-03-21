package com.rx.client.websoket;

import java.util.Map;

public interface ChainCodeMessageListener {

  public void receive(Map<String, String> result);

}
