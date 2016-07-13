package jp.bglb.bonboru.flux.example;

import jp.bglb.bonboru.flux.Dispatcher;
import jp.bglb.bonboru.flux.example.action.ActionTypes;
import jp.bglb.bonboru.flux.middleware.Middleware;
import jp.bglb.bonboru.flux.reducer.Reducer;
import jp.bglb.bonboru.flux.store.Store;

/**
 * Created by tmasuda on 2016/04/24.
 */
public class MyDispatcher<T> extends Dispatcher<T, ActionTypes> {

  public MyDispatcher(Reducer<T, ActionTypes> reducer, Store<T> store) {
    super(reducer, store);
  }

  public MyDispatcher(Reducer<T, ActionTypes> reducer, Store<T> store,
      Middleware<T, ActionTypes>... middlewares) {
    super(reducer, store, middlewares);
  }
}
