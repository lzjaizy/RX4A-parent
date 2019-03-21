package com.rx.client;

import java.util.Map;

/**
 * API response returned by API call.
 *
 * @param <T> The type of data that is deserialized from response body
 */
public class ApiResponse<T> {
  private final int statusCode;
  private final Map<String, String> headers;
  private final T data;

  /**
   * .
   *
   * @param statusCode The status code of HTTP response
   * @param headers    The headers of HTTP response
   */
  public ApiResponse(int statusCode, Map<String, String> headers) {
    this(statusCode, headers, null);
  }

  /**
   * .
   *
   * @param statusCode The status code of HTTP response
   * @param headers    The headers of HTTP response
   * @param data       The object deserialized from response bod
   */
  public ApiResponse(int statusCode, Map<String, String> headers, T data) {
    this.statusCode = statusCode;
    this.headers = headers;
    this.data = data;
  }

  public int getStatusCode() {
    return statusCode;
  }

  public Map<String, String> getHeaders() {
    return headers;
  }

  public T getData() {
    return data;
  }
}
