package com.example.kotlinexample.reducer

import com.example.kotlinexample.action.MyActionType
import com.example.kotlinexample.data.MainData
import com.example.kotlinexample.data.MainDataBuilder
import jp.bglb.bonboru.flux.action.ActionData

/**
 * Created by tetsuya on 2016/06/25.
 */
class MainReducer() : MyReducer<MainData>() {

  override fun received(currentState: MainData,
      actionData: ActionData<MainData, MyActionType>): MainData {
    return when (actionData.type) {
      MyActionType.UPDATE_MESSAGE -> MainDataBuilder().setMesage(actionData.data.mesage).build()

      else -> currentState
    }
  }

  override fun onError(currentState: MainData, error: Throwable): MainData {
    throw UnsupportedOperationException()
  }

}
