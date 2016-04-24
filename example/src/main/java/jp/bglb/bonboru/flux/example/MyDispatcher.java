package jp.bglb.bonboru.flux.example;

import jp.bglb.bonboru.flux.Dispatcher;
import jp.bglb.bonboru.flux.example.action.ActionTypes;
import jp.bglb.bonboru.flux.reducer.Reducer;
import jp.bglb.bonboru.flux.store.Store;

/**
 * Created by tmasuda on 2016/04/24.
 */
public class MyDispatcher<T, R> extends Dispatcher<T, ActionTypes, R> {

  public MyDispatcher(Reducer<T, ActionTypes, R> reducer, Store<T> store) {
    super(reducer, store);
  }

}
