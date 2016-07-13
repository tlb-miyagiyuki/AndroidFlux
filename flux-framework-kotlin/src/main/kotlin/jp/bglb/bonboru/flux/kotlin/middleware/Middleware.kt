package jp.bglb.bonboru.flux.kotlin.middleware

import jp.bglb.bonboru.flux.action.ActionData
import jp.bglb.bonboru.flux.action.ActionType
import jp.bglb.bonboru.flux.store.Store
import rx.Single

/**
 * Created by tetsuya on 2016/07/01.
 */
open class Middleware<T, E : ActionType>() {

  open fun before(store: Store<T>) {
  }

  open fun intercept(single: Single<ActionData<T, E>>): Single<ActionData<T, E>> {
    return single
  }

  open fun after(store: Store<T>, actionData: ActionData<T, E>): ActionData<T, E> {
    return actionData
  }

}
