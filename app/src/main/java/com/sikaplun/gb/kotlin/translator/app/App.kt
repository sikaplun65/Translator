package com.sikaplun.gb.kotlin.translator.app

import android.app.Application
import android.content.Context
import com.sikaplun.gb.kotlin.translator.di.component.AppComponent
import com.sikaplun.gb.kotlin.translator.di.component.DaggerAppComponent
import com.sikaplun.gb.kotlin.translator.di.module.RetrofitModule

class App : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent
            .factory()
            .create(RetrofitModule)
    }
}

val Context.appComponent: AppComponent
    get() =
        when (this) {
        is App -> appComponent
        else -> this.applicationContext.appComponent
    }