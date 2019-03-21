package com.rx.client.websoket;

import com.google.gson.Gson;
import com.rx.client.util.ConfigUtils;
import com.rx.client.util.Constants;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.SocketTimeoutException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Timer;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_17;
import org.java_websocket.handshake.ServerHandshake;

public class WSClient extends WebSocketClient {

  private static Log LOGGER = LogFactory.getLog(WSClient.class);
  private static final Map<String, WSClient> wsClients = new ConcurrentHashMap<>();
  private String token;
  private ChainCodeMessageListener listener;

  private WSClient(String url, String token)
      throws SocketTimeoutException, IllegalArgumentException, URISyntaxException {
    super(new URI(url + "?token=" + token), new Draft_17());
    if (StringUtils.isBlank(token)) {
      throw new IllegalArgumentException("Token is a must!");
    }
    this.token = token;
    wsClients.put(token, this);
    this.connect();
    int i = 0;
    long sleepTime = 1000;
    while (!this.getReadyState().equals(READYSTATE.OPEN)) {
      try {
        System.out.println("WSClient(token:" + token + "):Connecting...");
        Thread.sleep(sleepTime);
      } catch (Exception e) {
        e.printStackTrace();
      }
      i++;
      if (i >= 10) {
        System.out.println("WSClient(token:" + token + "):Connection timed out.");
        LOGGER.error("WSClient(token:" + token + "):Connection timed out.");
        throw new SocketTimeoutException("WSClient(token:" + token + "):Connection timed out.");
      }
    }
    /*开始发送心跳*/
    Timer timer = new Timer();
    timer.schedule(new TimeerTask(this), 1000, 2000);
  }

  @Override
  public void onOpen(ServerHandshake arg0) {
    System.out.println("WSClient(token:" + token + "):Connection succeeded");
  }

  @Override
  public void onMessage(String message) {
    /*忽略心跳消息*/
    if (StringUtils.isBlank(message) || "pong".equalsIgnoreCase(message)) {
      return;
    }
    System.out.println("WSClient(token:" + token + "):Receive server message-->" + message);
    /*处理验证失败*/
    if (message.startsWith("failure")) {
      System.out.println(message);
      LOGGER.error(message);
      return;
    }
    Map<String, String> result = null;
    try {
      // result = (Map<String,String>)JSONUtils.parse(message);
      result = new Gson().fromJson(message, HashMap.class);
    } catch (Exception e) {
      e.printStackTrace();
      LOGGER.error("Message parsing failed:" + message);
    }
    if (listener != null) {
      listener.receive(result);
    } else {
      LOGGER.error("WSClient(token:" + token + "):listener is null!");
    }
  }

  @Override
  public void onError(Exception arg0) {
    arg0.printStackTrace();
    System.out.println(
        "WSClient(token:" + token + "):An exception occurred and the connection was closed");
  }

  @Override
  public void onClose(int arg0, String arg1, boolean arg2) {
    System.out.println("WSClient(token:" + token + "):Connection is closed");
    if (wsClients.get(token) != null) {
      wsClients.remove(token);
    }
  }

  @Override
  public void onMessage(ByteBuffer bytes) {
    try {
      System.out.println(new String(bytes.array(), "utf-8"));
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
  }

  public void setChainCodeMessageListener(ChainCodeMessageListener listener) {
    this.listener = listener;
  }

  public static WSClient getInstance(String token) throws Exception {
    if (StringUtils.isBlank(token)) {
      throw new IllegalArgumentException("Token is a must!");
    }
    if (wsClients.get(token) != null) {
      return wsClients.get(token);
    } else {
      String url = null;
      Properties props = null;
      try {
        props = ConfigUtils.fromResourcePath(WSClient.class, "/ces-client.properties");
        if(props==null) {
          throw new Exception("未能在资源目录下读取到 ces-client.properties 配置文件!");
        }
      } catch (IOException ioe) {
        ioe.printStackTrace();
      }
      if (props != null) {
        url = props.getProperty(Constants.PROP_WS_SERVER_URL);
        if(StringUtils.isBlank(url)) {
          throw new Exception("未能在配置文件 ces-client.properties 中找到 websoket 服务端的连接配置. ");
        }
      }
      return new WSClient(url, token);
    }
  }

  public String getToken() {
    return token;
  }
}
