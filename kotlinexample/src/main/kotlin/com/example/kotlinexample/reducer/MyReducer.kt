package com.example.kotlinexample.reducer

import com.example.kotlinexample.action.MyActionType
import jp.bglb.bonboru.flux.reducer.Reducer

/**
 * Created by tetsuya on 2016/06/25.
 */
abstract class MyReducer<T>() : Reducer<T, MyActionType>()
