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

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public List<String> getMessages() {
    return messages;
  }

  public void setMessages(List<String> messages) {
    this.messages = messages;
  }

  public String getError() {
    return error;
  }

  public void setError(String error) {
    this.error = error;
  }

  public boolean isProgressBarVisibility() {
    return progressBarVisibility;
  }

  public void setProgressBarVisibility(boolean progressBarVisibility) {
    this.progressBarVisibility = progressBarVisibility;
  }
}

