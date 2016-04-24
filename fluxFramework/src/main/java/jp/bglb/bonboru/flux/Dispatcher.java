package jp.bglb.bonboru.flux;

import jp.bglb.bonboru.flux.action.Action;
import jp.bglb.bonboru.flux.action.ActionData;
import jp.bglb.bonboru.flux.action.ActionType;
import jp.bglb.bonboru.flux.reducer.Reducer;
import jp.bglb.bonboru.flux.store.Store;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by tmasuda on 2016/04/14.
 * Actionで取得したデータをstoreにdispatchする
 */
public class Dispatcher<T, E extends ActionType, R> {
  final Reducer<T, E, R> reducer;
  final Store<T> store;

  public Dispatcher(final Reducer<T, E, R> reducer, final Store<T> store) {
    this.reducer = reducer;
    this.store = store;
  }

  public void dispatch(final Action<R, E> action) {
    Observable<ActionData<R, E>> observable =
        Observable.create(new Observable.OnSubscribe<ActionData<R, E>>() {
          @Override public void call(Subscriber<? super ActionData<R, E>> subscriber) {
            if (subscriber.isUnsubscribed()) {
              return;
            }
            ActionData<R, E> actionData = action.execute();
            subscriber.onNext(actionData);
            subscriber.onCompleted();
          }
        });

    observable.subscribeOn(Schedulers.io()).subscribe(new Action1<ActionData<R, E>>() {
      @Override public void call(ActionData<R, E> actionData) {
        T data = reducer.received(store.getData(), actionData);
        store.setData(data);
      }
    }, new Action1<Throwable>() {
      @Override public void call(Throwable throwable) {
        T data = reducer.onError(store.getData(), throwable);
        store.setData(data);
      }
    });
  }
}
