package com.rx.client;

public interface ApiService {

  /**
   * 根据 key 获取配置项。
   *
   * @param key
   * @return
   */
  String getConfiguration(String key);

  /**
   * 获取服务上下文信息。
   *
   * @return appContext instance
   */
  AppContext getAppContext();

}
