package jp.bglb.bonboru.fluxutility.middlewares

import jp.bglb.bonboru.flux.action.ActionType
import jp.bglb.bonboru.flux.middleware.Middleware
import jp.bglb.bonboru.flux.store.Store
import jp.bglb.bonboru.fluxutility.dtos.markers.AsyncLoadable
import jp.bglb.bonboru.fluxutility.types.RequestStatus.REQUESTING

/**
 * Created by Tetsuya Masuda on 2016/07/01.
 */
class ContentLoadMiddleware<T : AsyncLoadable, E : ActionType>() : Middleware<T, E>() {

  override fun before(store: Store<T>) {
    val data = store.copyCurrentState()
    data.updateRequestStatus(REQUESTING)
    store.data = data
  }

}
