package com.example.kotlinexample

import com.example.kotlinexample.action.MyActionType
import jp.bglb.bonboru.flux.kotlin.Dispatcher
import jp.bglb.bonboru.flux.reducer.Reducer
import jp.bglb.bonboru.flux.store.Store

/**
 * Created by tetsuya on 2016/06/25.
 */
class MyDispatcher<T>(reducer: Reducer<T, MyActionType>, store: Store<T>) : Dispatcher<T, MyActionType>(
    reducer, store) {
}
