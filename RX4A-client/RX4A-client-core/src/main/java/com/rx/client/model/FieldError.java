package com.rx.client.model;

import java.util.Objects;

public class FieldError {
  private String fieldName;
  private String message;

  public String getFieldName() {
    return fieldName;
  }

  public void setFieldName(String fieldName) {
    this.fieldName = fieldName;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof FieldError)) {
      return false;
    }
    FieldError that = (FieldError) o;
    return Objects.equals(getFieldName(), that.getFieldName()) &&
        Objects.equals(getMessage(), that.getMessage());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getFieldName(), getMessage());
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("FieldError{");
    sb.append("fieldName='").append(fieldName).append('\'');
    sb.append(", message='").append(message).append('\'');
    sb.append('}');
    return sb.toString();
  }
}
