package jp.bglb.bonboru.flux.store;

/**
 * Created by tmasuda on 2016/04/14.
 * データストア. Actionから渡ってくるデータを保持するクラス.
 */
public abstract class Store<T> {

  protected T data;

  public abstract void setData(T data);

  public T getData() {
    return this.data;
  }

  public abstract T copyCurrentState();
}
