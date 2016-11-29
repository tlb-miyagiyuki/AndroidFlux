package jp.bglb.bonboru.flux.kotlin

import jp.bglb.bonboru.flux.action.ActionData
import jp.bglb.bonboru.flux.action.ActionType
import jp.bglb.bonboru.flux.kotlin.middleware.Middleware
import jp.bglb.bonboru.flux.reducer.Reducer
import jp.bglb.bonboru.flux.store.Store
import rx.Observable

/**
 * Created by tetsuya on 2016/06/25.
 */
open class Dispatcher<T, E : ActionType>(val reducer: Reducer<T, E>, val store: Store<T>, vararg val middleWares: Middleware<T, E> = arrayOf()) {

  fun dispatch(vararg actions: () -> ActionData<T, E> = arrayOf()) {
    var observable = Observable.create<ActionData<T, E>> { subscriber ->
      if (subscriber.isUnsubscribed) {
        return@create
      }

      actions.forEach {
        val actionData = it()
        subscriber.onNext(actionData)
      }
    }

    middleWares.forEach {
      it.before(store)
      observable = it.intercept(observable)
    }

    observable.subscribe({ actionData ->
      var data = actionData
      middleWares.forEach {
        data = it.after(store, data)
      }
      store.data = reducer.received(store.copyCurrentState(), data)
    }, {
      store.data = reducer.onError(store.copyCurrentState(), it)
    })
  }
}
