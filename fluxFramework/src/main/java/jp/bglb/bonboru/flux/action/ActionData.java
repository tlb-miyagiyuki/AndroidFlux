package jp.bglb.bonboru.flux.action;

/**
 * Created by tmasuda on 2016/04/15.
 */
public class ActionData<T, E extends ActionType> {
  public final E type;

  public final T data;

  public ActionData(T data, E type) {
    this.type = type;
    this.data = data;
  }
}
