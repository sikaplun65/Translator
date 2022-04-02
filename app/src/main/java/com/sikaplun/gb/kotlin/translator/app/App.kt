package com.sikaplun.gb.kotlin.translator.app

import android.app.Application
import com.sikaplun.gb.kotlin.translator.di.module.appModule
import com.sikaplun.gb.kotlin.translator.di.module.dataModule
import com.sikaplun.gb.kotlin.translator.di.module.retrofitModule
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(level = Level.DEBUG)
            modules(listOf(appModule, dataModule, retrofitModule))
        }
    }
}