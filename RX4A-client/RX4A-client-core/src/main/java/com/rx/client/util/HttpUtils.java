/** */
package com.rx.client.util;

import java.util.HashMap;
import java.util.Map;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;

/**
 * Http 工具类，用于提供常用的方法.
 *
 * @author yang.tiedang@cesgroup.com.cn
 */
public class HttpUtils {

  /**
   * 将 Http header 转换成字符串类型 - 每个 header 一行.
   *
   * @param headers headers array.
   * @return headers as formatted string.
   */
  public static String toString(Header[] headers) {
    if (headers == null) {
      return null;
    }
    StringBuilder sb = new StringBuilder();
    for (Header header : headers) {
      sb.append(header.getName()).append(":").append(header.getValue()).append("\n");
    }
    return sb.toString();
  }

  /**
   * Get contentType from entity.
   *
   * @param entity the http entity.
   * @return the contentType attribute
   */
  public static ContentType getContentType(final HttpEntity entity) {
    if (entity == null) {
      return null;
    }
    Header typeH = entity.getContentType();
    if (typeH == null) {
      return null;
    }

    String value = typeH.getValue();
    if (value == null) {
      return null;
    }

    return ContentType.parse(value);
  }

  /**
   * Convert the headers array to map structure.
   *
   * @param headers headers array.
   * @return the headers as map object.
   */
  public static Map<String, String> convertHeaders(Header[] headers) {
    Map<String, String> hds = new HashMap<>();
    if (headers != null && headers.length > 0) {
      for (Header header: headers) {
        hds.put(header.getName(),header.getValue());
      }
    }
    return hds;
  }
}
