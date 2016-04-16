package jp.bglb.bonboru.flux.store;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tmasuda on 2016/04/14.
 * データストア. Actionから渡ってくるデータを保持するクラス.
 */
public class Store<T> {

  private T data;

  private List<StoreChangeListener<T>> listeners = new ArrayList<>();

  public void addListener(StoreChangeListener<T> listener) {
    if (listeners.indexOf(listener) > 0) {
      return;
    }
    listeners.add(listener);
  }

  public void removeListener(StoreChangeListener<T> listener) {
    listeners.remove(listener);
  }

  public void setData(T data) {
    this.data = data;
    for (StoreChangeListener<T> l : listeners) {
      l.onChange(this.data);
    }
  }

  public T getData() {
    return this.data;
  }
}
