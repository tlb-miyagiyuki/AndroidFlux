package jp.bglb.bonboru.flux.middleware;

import jp.bglb.bonboru.flux.action.ActionData;
import jp.bglb.bonboru.flux.action.ActionType;
import jp.bglb.bonboru.flux.store.Store;
import rx.Observable;

/**
 * Created by Tetsuya Masuda on 2016/07/01.
 */

public class Middleware<T, E extends ActionType> {

  public void before(Store<T> store) {
  }

  public Observable<ActionData<T, E>> intercept(Observable<ActionData<T, E>> observable) {
    return observable;
  }

  public ActionData<T, E> after(Store<T> store, ActionData<T, E> actionData) {
    return actionData;
  }
}
