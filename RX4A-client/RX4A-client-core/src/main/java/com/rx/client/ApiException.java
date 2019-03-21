package com.rx.client;

import java.io.IOException;
import java.util.Map;

/**
 * 自定义异常类，用于处理 Rest API 调用异常.
 *
 * @author yang.tiedang@cesgroup.com.cn
 */
public class ApiException extends IOException {
  private int statusCode = 0;
  private Map<String, String> responseHeaders = null;
  private String responseBody = null;

  public ApiException() {}

  public ApiException(Throwable throwable) {
    super(throwable);
  }

  public ApiException(String message) {
    super(message);
  }

  /**
   * .
   *
   * @param message message
   * @param throwable throwable
   * @param statusCode code
   */
  public ApiException(
      String message,
      Throwable throwable,
      int statusCode,
      Map<String, String> responseHeaders,
      String responseBody) {
    super(message, throwable);
    this.statusCode = statusCode;
    this.responseHeaders = responseHeaders;
    this.responseBody = responseBody;
  }

  public ApiException(
      String message, int statusCode, Map<String, String> responseHeaders, String responseBody) {
    this(message, (Throwable) null, statusCode, responseHeaders, responseBody);
  }

  public ApiException(
      String message, Throwable throwable, int statusCode, Map<String, String> responseHeaders) {
    this(message, throwable, statusCode, responseHeaders, null);
  }

  public ApiException(int statusCode, Map<String, String> responseHeaders, String responseBody) {
    this((String) null, (Throwable) null, statusCode, responseHeaders, responseBody);
  }

  public ApiException(int statusCode, String message) {
    super(message);
    this.statusCode = statusCode;
  }

  /** . */
  public ApiException(
      int statusCode, String message, Map<String, String> responseHeaders, String responseBody) {
    this(statusCode, message);
    this.responseHeaders = responseHeaders;
    this.responseBody = responseBody;
  }

  /**
   * Get the HTTP status code.
   *
   * @return HTTP status code
   */
  public int getStatusCode() {
    return statusCode;
  }

  /**
   * Get the HTTP response headers.
   *
   * @return A map of list of string
   */
  public Map<String, String> getResponseHeaders() {
    return responseHeaders;
  }

  /**
   * Get the HTTP response body.
   *
   * @return Response body in the form of string
   */
  public String getResponseBody() {
    return responseBody;
  }
}
