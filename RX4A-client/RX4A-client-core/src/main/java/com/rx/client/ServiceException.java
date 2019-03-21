package com.rx.client;

import java.io.IOException;

public class ServiceException extends IOException {
  private static final long serialVersionUID = 1000L;

  private String code;

  public ServiceException() {
    super();
    this.code = "-1";
  }

  public ServiceException(String code, String message) {
    super(message);
    this.code = code;
  }

  public ServiceException(String code, String message, Throwable cause) {
    super(message, cause);
    this.code = code;
  }

}
