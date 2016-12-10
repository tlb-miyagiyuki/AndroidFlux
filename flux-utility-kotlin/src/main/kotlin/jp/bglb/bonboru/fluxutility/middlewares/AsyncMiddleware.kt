package jp.bglb.bonboru.fluxutility.middlewares

import jp.bglb.bonboru.flux.action.ActionData
import jp.bglb.bonboru.flux.action.ActionType
import jp.bglb.bonboru.flux.kotlin.middleware.Middleware
import jp.bglb.bonboru.flux.store.Store
import jp.bglb.bonboru.fluxutility.dtos.markers.AsyncLoadable
import jp.bglb.bonboru.fluxutility.types.RequestStatus.REQUESTING
import rx.Observable
import rx.schedulers.Schedulers

/**
 * Created by Tetsuya Masuda on 2016/08/09.
 */
class AsyncMiddleware<T : AsyncLoadable, E : ActionType>() : Middleware<T, E>() {
  override fun intercept(observable: Observable<ActionData<T, E>>): Observable<ActionData<T, E>> {
    return observable.subscribeOn(Schedulers.io())
  }

  override fun before(store: Store<T>) {
    val data = store.copyCurrentState()
    data.updateRequestStatus(REQUESTING)
    store.data = data
  }
}
