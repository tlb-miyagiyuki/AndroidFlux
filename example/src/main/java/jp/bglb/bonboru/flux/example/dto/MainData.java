package jp.bglb.bonboru.flux.example.dto;

import android.os.Build;

import jp.bglb.bonboru.flux.compiler.annotation.ObservableClass;
import jp.bglb.bonboru.flux.compiler.annotation.ObservableField;
import rx.subjects.BehaviorSubject;

/**
 * Created by tmasuda on 2016/04/15.
 */
@ObservableClass(MainData.class) public class MainData {

  @ObservableField(String.class) public String message;

}
