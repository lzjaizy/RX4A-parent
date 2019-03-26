package com.rx.utils;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by jason on 2018/1/23.
 */
public class ResponseMessage {
  //成功统一给Success  失败统一给Failed
  @JsonProperty("code")
  private String code;
  @JsonProperty("message")
  private String message;
  @JsonProperty("data")
  private Object data;

  public String getCode() {
    return code;
  }

  public String getMessage() {
    return message;
  }

  public Object getData() {
    return data;
  }

  public ResponseMessage setCode(String code) {
    this.code = code;
    return this;
  }

  public ResponseMessage setMessage(String message) {
    this.message = message;
    return this;
  }

  public ResponseMessage setData(Object data) {
    this.data = data;
    return this;
  }
}
