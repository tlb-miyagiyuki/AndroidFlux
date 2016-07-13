package jp.bglb.bonboru.flux.kotlin

import jp.bglb.bonboru.flux.action.ActionData
import jp.bglb.bonboru.flux.action.ActionType
import jp.bglb.bonboru.flux.kotlin.middleware.Middleware
import jp.bglb.bonboru.flux.reducer.Reducer
import jp.bglb.bonboru.flux.store.Store
import rx.Single
import rx.schedulers.Schedulers

/**
 * Created by tetsuya on 2016/06/25.
 */
open class Dispatcher<T, E : ActionType>(val reducer: Reducer<T, E>, val store: Store<T>, vararg val middleWares: Middleware<T, E> = arrayOf()) {

  fun dispatch(action: () -> ActionData<T, E>) {
    var observable = Single.create<ActionData<T, E>> {
      if (it.isUnsubscribed) {
        return@create
      }
      val actionData = action()
      if (it.isUnsubscribed) {
        return@create
      }
      it.onSuccess(actionData)
    }.subscribeOn(Schedulers.io())

    middleWares.forEach {
      it.before(store)
      observable = it.intercept(observable)
    }

    observable.subscribe({ actionData ->
      var data = actionData
      middleWares.forEach {
        data = it.after(store, data)
      }
      store.data = reducer.received(store.data, data)
    }, {
      store.data = reducer.onError(store.data, it)
    })
  }
}
