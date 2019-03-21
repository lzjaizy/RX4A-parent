package com.rx.client.websoket;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class WSPropertiesUtils {
  private static final Log LOGGER = LogFactory.getLog(WSPropertiesUtils.class);

  /**
   * 根据需要获取的参数名返回相应的数据.
   *
   * @param key 属性名称
   * @return 属性值
   */
  public static String getParam(String key) {
    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    InputStream inStream = classLoader.getResourceAsStream("/cesClient.properties");
    Properties properties = new Properties();
    try {
      properties.load(inStream);
    } catch (IOException ex) {
      ex.printStackTrace();
      LOGGER.error(ex);
    } finally {
      try {
        inStream.close();
      } catch (IOException ioe) {
        LOGGER.warn(ioe);
      }
    }
    return properties.getProperty(key);
  }
}
