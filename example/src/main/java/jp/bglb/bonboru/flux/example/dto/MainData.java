package jp.bglb.bonboru.flux.example.dto;

import android.os.Build;

import java.util.List;
import jp.bglb.bonboru.flux.compiler.annotation.ObservableClass;
import jp.bglb.bonboru.flux.compiler.annotation.ObservableField;
import rx.subjects.BehaviorSubject;

/**
 * Created by tmasuda on 2016/04/15.
 */
@ObservableClass(MainData.class) public class MainData {

  @ObservableField(String.class) public String message;

  @ObservableField(value = List.class, types = { String.class }) public List<String> messages;

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
}
