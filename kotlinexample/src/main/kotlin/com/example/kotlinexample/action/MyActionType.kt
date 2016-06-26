package com.example.kotlinexample.action

import jp.bglb.bonboru.flux.action.ActionType

/**
 * Created by tetsuya on 2016/06/25.
 */
enum class MyActionType() : ActionType {
  UPDATE_MESSAGE, ERROR
}
