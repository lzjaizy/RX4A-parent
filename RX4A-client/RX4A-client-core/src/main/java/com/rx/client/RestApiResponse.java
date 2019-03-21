package com.rx.client;

import com.rx.client.model.FieldError;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class RestApiResponse<T> {
  private int statusCode;
  private Map<String, String> headers;

  @SerializedName("success")
  private boolean success;

  @SerializedName("code")
  private String code;

  @SerializedName("message")
  private String message;

  @SerializedName("fieldErrors")
  private List<FieldError> fieldErrors = new ArrayList<>();

  @SerializedName("exception")
  private Exception exception;

  @SerializedName("data")
  private T data;

  public RestApiResponse() {}

  public RestApiResponse(int statusCode, Map<String, String> headers) {
    this.statusCode = statusCode;
    this.headers = headers;
  }

  public RestApiResponse(int statusCode, Map<String, String> headers, T data) {
    this.statusCode = statusCode;
    this.headers = headers;
    this.data = data;
  }

  public int getStatusCode() {
    return statusCode;
  }

  public void setStatusCode(int statusCode) {
    this.statusCode = statusCode;
  }

  public Map<String, String> getHeaders() {
    return headers;
  }

  public void setHeaders(Map<String, String> headers) {
    this.headers = headers;
  }

  public boolean isSuccess() {
    return success;
  }

  public void setSuccess(boolean success) {
    this.success = success;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public List<FieldError> getFieldErrors() {
    return fieldErrors;
  }

  public void setFieldErrors(List<FieldError> fieldErrors) {
    this.fieldErrors = fieldErrors;
  }

  public Exception getException() {
    return exception;
  }

  public void setException(Exception exception) {
    this.exception = exception;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    RestApiResponse<?> that = (RestApiResponse<?>) o;
    return statusCode == that.statusCode
        && success == that.success
        && Objects.equals(headers, that.headers)
        && Objects.equals(code, that.code)
        && Objects.equals(message, that.message)
        && Objects.equals(fieldErrors, that.fieldErrors)
        && Objects.equals(exception, that.exception)
        && Objects.equals(data, that.data);
  }

  @Override
  public int hashCode() {
    return Objects.hash(statusCode, headers, success, code, message, fieldErrors, exception, data);
  }

  @Override
  public String toString() {
    return "RestApiResponse{"
        + "statusCode="
        + statusCode
        + ", headers="
        + headers
        + ", success="
        + success
        + ", code='"
        + code
        + '\''
        + ", message='"
        + message
        + '\''
        + ", fieldErrors="
        + fieldErrors
        + ", exception="
        + exception
        + ", data="
        + data
        + '}';
  }
}
