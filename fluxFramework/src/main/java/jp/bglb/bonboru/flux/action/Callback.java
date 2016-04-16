package jp.bglb.bonboru.flux.action;

/**
 * Created by tmasuda on 2016/04/14.
 */
public interface Callback<T> {

  void callback(T data);

}
