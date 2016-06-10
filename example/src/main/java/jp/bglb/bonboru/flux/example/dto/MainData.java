package jp.bglb.bonboru.flux.example.dto;

import jp.bglb.bonboru.flux.compiler.annotation.ObservableClass;
import jp.bglb.bonboru.flux.compiler.annotation.ObservableField;
import rx.subjects.BehaviorSubject;

/**
 * Created by tmasuda on 2016/04/15.
 */
@ObservableClass(MainData.class)
public class MainData {

  @ObservableField(String.class)
  public final String message;

  @ObservableField(String.class)
  public final String text;

  public MainData(String message) {
    this.message = message;
    this.text = "";
  }

  public MainData(String message, String text) {
    this.message = message;
    this.text = text;
  }
}
