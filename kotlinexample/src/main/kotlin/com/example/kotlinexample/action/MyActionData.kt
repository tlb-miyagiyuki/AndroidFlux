package com.example.kotlinexample.action

import jp.bglb.bonboru.flux.action.ActionData

/**
 * Created by tetsuya on 2016/06/25.
 */
class MyActionData<T>(var data: T, var type: MyActionType) : ActionData<T, MyActionType>(data, type)
