package jp.bglb.bonboru.flux.middleware;

import jp.bglb.bonboru.flux.action.ActionData;
import jp.bglb.bonboru.flux.action.ActionType;
import jp.bglb.bonboru.flux.store.Store;
import rx.Single;

/**
 * Created by Tetsuya Masuda on 2016/07/01.
 */

public class Middleware<T, E extends ActionType> {

  public void before(Store<T> store) {
  }

  public Single<ActionData<T, E>> intercept(Single<ActionData<T, E>> single) {
    return single;
  }

  public ActionData<T, E> after(Store<T> store, ActionData<T, E> actionData) {
    return actionData;
  }
}
