package com.example.kotlinexample.middleware

import com.example.kotlinexample.action.MyActionType
import com.example.kotlinexample.data.MainData
import com.example.kotlinexample.data.MainDataBuilder
import jp.bglb.bonboru.flux.action.ActionData
import jp.bglb.bonboru.flux.kotlin.middleware.Middleware
import jp.bglb.bonboru.flux.store.Store
import rx.Observable
import rx.schedulers.Schedulers

/**
 * Created by tetsuya on 2016/07/01.
 */
class LoadMiddleware() : Middleware<MainData, MyActionType>() {

  override fun before(store: Store<MainData>) {
    store.data = MainDataBuilder().setProgressVisibility(true).setMesage("waiting...").build()
  }

  override fun intercept(
      observable: Observable<ActionData<MainData, MyActionType>>): Observable<ActionData<MainData, MyActionType>> {
    return observable.subscribeOn(Schedulers.io())
  }
}
