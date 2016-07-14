package jp.bglb.bonboru.flux.example.dto;

import java.util.List;
import jp.bglb.bonboru.flux.processor.annotation.ObservableClass;
import jp.bglb.bonboru.flux.processor.annotation.ObservableField;
import jp.bglb.bonboru.flux.processor.type.CheckType;

/**
 * Created by tmasuda on 2016/04/15.
 */
@ObservableClass public class MainData {

  @ObservableField(hasDefaultValue = true) public String message;

  @ObservableField(checkType = CheckType.NULLABLE) public String error;

  @ObservableField public List<String> messages;

  @ObservableField public boolean progressBarVisibility;

  @ObservableField private boolean isBoolean;

  @ObservableField private String isString;

  @ObservableField private boolean is_boolean;

  @ObservableField private String is_string;

  @ObservableField private String isaString;

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getError() {
    return error;
  }

  public void setError(String error) {
    this.error = error;
  }

  public List<String> getMessages() {
    return messages;
  }

  public void setMessages(List<String> messages) {
    this.messages = messages;
  }

  public boolean isProgressBarVisibility() {
    return progressBarVisibility;
  }

  public void setProgressBarVisibility(boolean progressBarVisibility) {
    this.progressBarVisibility = progressBarVisibility;
  }

  public boolean isBoolean() {
    return isBoolean;
  }

  public void setBoolean(boolean aBoolean) {
    isBoolean = aBoolean;
  }

  public String getIsString() {
    return isString;
  }

  public void setIsString(String isString) {
    this.isString = isString;
  }

  public boolean is_boolean() {
    return is_boolean;
  }

  public void setIs_boolean(boolean is_boolean) {
    this.is_boolean = is_boolean;
  }

  public String getIs_string() {
    return is_string;
  }

  public void setIs_string(String is_string) {
    this.is_string = is_string;
  }

  public String getIsaString() {
    return isaString;
  }

  public void setIsaString(String isaString) {
    this.isaString = isaString;
  }
}

