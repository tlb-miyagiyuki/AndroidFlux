package jp.bglb.bonboru.flux.kotlin

import jp.bglb.bonboru.flux.action.ActionData
import jp.bglb.bonboru.flux.action.ActionType
import jp.bglb.bonboru.flux.reducer.Reducer
import jp.bglb.bonboru.flux.store.Store
import rx.Single
import rx.schedulers.Schedulers

/**
 * Created by tetsuya on 2016/06/25.
 */
open class Dispatcher<T, E : ActionType>(val reducer: Reducer<T, E>, val store: Store<T>) {

  fun dispatch(action: () -> ActionData<T, E>) {
    val observable = Single.create<ActionData<T, E>> {
      val actionData = action()
      if (it.isUnsubscribed) {
        return@create
      }
      it.onSuccess(actionData)
    }

    observable.subscribeOn(Schedulers.io())
        .subscribe({
          store.data = reducer.received(store.data, it)
        }, {
          store.data = reducer.onError(store.data, it)
        })
  }

}
