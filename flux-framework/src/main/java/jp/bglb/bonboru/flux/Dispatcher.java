package jp.bglb.bonboru.flux;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import jp.bglb.bonboru.flux.action.Action;
import jp.bglb.bonboru.flux.action.ActionData;
import jp.bglb.bonboru.flux.action.ActionType;
import jp.bglb.bonboru.flux.middleware.Middleware;
import jp.bglb.bonboru.flux.reducer.Reducer;
import jp.bglb.bonboru.flux.store.Store;
import rx.Single;
import rx.SingleSubscriber;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by tmasuda on 2016/04/14.
 * Actionで取得したデータをstoreにdispatchする
 */
public class Dispatcher<T, E extends ActionType> {
  private final Reducer<T, E> reducer;
  private final Store<T> store;
  private final List<Middleware<T, E>> middlewares;

  public Dispatcher(final Reducer<T, E> reducer, final Store<T> store) {
    this.reducer = reducer;
    this.store = store;
    this.middlewares = new ArrayList<>();
  }

  public Dispatcher(final Reducer<T, E> reducer, final Store<T> store,
      Middleware<T, E>... middlewares) {
    this.reducer = reducer;
    this.store = store;
    this.middlewares = Arrays.asList(middlewares);
  }

  public void dispatch(final Action<T, E> action) {
    Single<ActionData<T, E>> observable = Single.create(new Single.OnSubscribe<ActionData<T, E>>() {
      @Override public void call(SingleSubscriber<? super ActionData<T, E>> singleSubscriber) {
        if (singleSubscriber.isUnsubscribed()) {
          return;
        }
        ActionData<T, E> actionData = null;
        try {
          actionData = action.execute();
          if (singleSubscriber.isUnsubscribed()) {
            return;
          }
          singleSubscriber.onSuccess(actionData);
        } catch (Throwable throwable) {
          singleSubscriber.onError(throwable);
        }
      }
    });

    for (Middleware<T, E> middleware : middlewares) {
      middleware.before(store);
      observable = middleware.intercept(observable);
    }

    observable.subscribe(new Action1<ActionData<T, E>>() {
      @Override public void call(ActionData<T, E> actionData) {
        for (Middleware<T, E> middleware : middlewares) {
          actionData = middleware.after(store, actionData);
        }
        T data = reducer.received(store.copyCurrentState(), actionData);
        store.setData(data);
      }
    }, new Action1<Throwable>() {
      @Override public void call(Throwable throwable) {
        T data = reducer.onError(store.copyCurrentState(), throwable);
        store.setData(data);
      }
    });
  }
}
