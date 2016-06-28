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
public class Dispatcher<T, E extends ActionType> {
  final Reducer<T, E> reducer;
  final Store<T> store;

  public Dispatcher(final Reducer<T, E> reducer, final Store<T> store) {
    this.reducer = reducer;
    this.store = store;
  }

  public void dispatch(final Action<T, E> action) {
    Observable<ActionData<T, E>> observable =
        Observable.create(new Observable.OnSubscribe<ActionData<T, E>>() {
          @Override public void call(Subscriber<? super ActionData<T, E>> subscriber) {
            if (subscriber.isUnsubscribed()) {
              return;
            }
            ActionData<T, E> actionData = action.execute();
            subscriber.onNext(actionData);
            subscriber.onCompleted();
          }
        });

    observable.subscribeOn(Schedulers.io()).subscribe(new Action1<ActionData<T, E>>() {
      @Override public void call(ActionData<T, E> actionData) {
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

  public void dispatchSync(final Action<T, E> action) {
    Observable<ActionData<T, E>> observable =
        Observable.create(new Observable.OnSubscribe<ActionData<T, E>>() {
          @Override public void call(Subscriber<? super ActionData<T, E>> subscriber) {
            if (subscriber.isUnsubscribed()) {
              return;
            }
            ActionData<T, E> actionData = action.execute();
            subscriber.onNext(actionData);
            subscriber.onCompleted();
          }
        });

    observable.subscribe(new Action1<ActionData<T, E>>() {
      @Override public void call(ActionData<T, E> actionData) {
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
