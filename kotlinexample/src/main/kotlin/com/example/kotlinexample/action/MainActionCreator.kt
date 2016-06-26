package com.example.kotlinexample.action

import com.example.kotlinexample.data.MainData
import com.example.kotlinexample.data.MainDataBuilder

/**
 * Created by tetsuya on 2016/06/25.
 */
class MainActionCreator() {

  fun action(message: String): () -> MyActionData<MainData> {
    return fun(): MyActionData<MainData> {
      return MyActionData<MainData>(MainDataBuilder().setMesage(message).build(),
          MyActionType.UPDATE_MESSAGE)
    }
  }

}
