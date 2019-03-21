package com.rx.client.model;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * ErrorMessage bean.
 *
 */
public class ErrorMessage {
  @SerializedName("code")
  private String code = null;

  @SerializedName("message")
  private String message = null;

  @SerializedName("fieldErrors")
  private List<FieldError> fieldErrors;

  public ErrorMessage (String code, String message) {
    this(code, message, null);
  }

  public ErrorMessage (String code, String message, List<FieldError> fieldErrors) {
    this.code = code;
    this.message = message;
    if (fieldErrors != null) {
      this.fieldErrors = fieldErrors;
    } else {
      this.fieldErrors = new ArrayList<>();
    }
  }

  public void addError(FieldError fieldError) {
    fieldErrors.add(fieldError);
  }

  public void addErrors(List<FieldError> errors) {
    fieldErrors.addAll(errors);
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ErrorMessage)) {
      return false;
    }
    ErrorMessage that = (ErrorMessage) o;
    return Objects.equals(getCode(), that.getCode()) &&
        Objects.equals(getMessage(), that.getMessage()) &&
        Objects.equals(getFieldErrors(), that.getFieldErrors());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getCode(), getMessage(), getFieldErrors());
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("ErrorMessage{");
    sb.append("code='").append(code).append('\'');
    sb.append(", message='").append(message).append('\'');
    sb.append(", fieldErrors=").append(fieldErrors);
    sb.append('}');
    return sb.toString();
  }
}

