package com.example.kotlinexample.action

import com.example.kotlinexample.data.MainData
import com.example.kotlinexample.data.MainDataBuilder

/**
 * Created by tetsuya on 2016/06/25.
 */
class MainActionCreator() {

  fun action(message: String): () -> MyActionData<MainData> {
    return fun(): MyActionData<MainData> {
      Thread.sleep(5000)
      return MyActionData<MainData>(
          MainDataBuilder().setMesage(message).setProgressVisibility(false).build(),
          MyActionType.UPDATE_MESSAGE)
    }
  }

}
