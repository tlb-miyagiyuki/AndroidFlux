package com.example.kotlinexample.middleware

import com.example.kotlinexample.action.MyActionType
import com.example.kotlinexample.data.MainData
import com.example.kotlinexample.data.MainDataBuilder
import jp.bglb.bonboru.flux.kotlin.middleware.Middleware
import jp.bglb.bonboru.flux.store.Store

/**
 * Created by tetsuya on 2016/07/01.
 */
class LoadMiddleware() : Middleware<MainData, MyActionType>() {

  override fun before(store: Store<MainData>) {
    store.data = MainDataBuilder().setProgressVisibility(true).setMesage(store.data.mesage).build()
  }

}
