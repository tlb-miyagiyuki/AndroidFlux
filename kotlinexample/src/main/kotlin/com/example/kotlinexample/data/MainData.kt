package com.example.kotlinexample.data

import jp.bglb.bonboru.flux.compiler.annotation.ObservableClass
import jp.bglb.bonboru.flux.compiler.annotation.ObservableField

/**
 * Created by tetsuya on 2016/06/25.
 */
@ObservableClass
data class MainData(@ObservableField var mesage: String = "")
