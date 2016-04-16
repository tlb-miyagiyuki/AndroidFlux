package jp.bglb.bonboru.flux.component;

import jp.bglb.bonboru.flux.store.StoreChangeListener;
import rx.Observable;
import rx.subjects.BehaviorSubject;

/**
 * Created by tmasuda on 2016/04/14.
 * Viewのstateを保持するクラス.
 * View側のClassがFieldに保持し、このクラスからデータを参照し自身の表示に反映することを想定している.
 */
public class Component<T> implements StoreChangeListener<T> {

  private final BehaviorSubject<T> state = BehaviorSubject.create();

  public final T getState() {
    return this.state.getValue();
  }

  @Override public final void onChange(T data) {
    this.state.onNext(data);
  }

  public final Observable<T> subscribe() {
    return this.state;
  }
}
