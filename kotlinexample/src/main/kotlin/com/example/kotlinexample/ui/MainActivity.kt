package com.example.kotlinexample.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
import com.example.kotlinexample.R
import com.example.kotlinexample.action.MainActionCreator
import com.example.kotlinexample.action.MyActionType
import com.example.kotlinexample.data.MainData
import com.example.kotlinexample.data.MainDataStore
import com.example.kotlinexample.reducer.MainReducer
import jp.bglb.bonboru.flux.kotlin.Dispatcher
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import rx.subscriptions.CompositeSubscription

/**
 * Created by tetsuya on 2016/06/25.
 */
class MainActivity() : AppCompatActivity() {

  lateinit var message: TextView

  lateinit var button: Button

  lateinit var button2: Button

  lateinit var button3: Button

  lateinit var store: MainDataStore

  lateinit var reducer: MainReducer

  lateinit var dispatcher: Dispatcher<MainData, MyActionType>

  lateinit var subscription: CompositeSubscription

  var actionCreator = MainActionCreator()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    message = findViewById(R.id.message) as TextView
    button = findViewById(R.id.button) as Button
    button2 = findViewById(R.id.button2) as Button
    button3 = findViewById(R.id.button3) as Button

    button.setOnClickListener { dispatcher.dispatch(actionCreator.action("hello")) }
    button2.setOnClickListener { dispatcher.dispatch(actionCreator.action("hello, Flux")) }
    button3.setOnClickListener { dispatcher.dispatch(actionCreator.action("hello, Flux, Kotlin")) }

    reducer = MainReducer()
    store = MainDataStore()
    dispatcher = Dispatcher(reducer, store)
  }

  override fun onResume() {
    super.onResume()
    subscription = CompositeSubscription(
        store.mesage
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
              message.text = it
            })
    )
  }

  override fun onPause() {
    super.onPause()
    subscription.unsubscribe()
  }
}
