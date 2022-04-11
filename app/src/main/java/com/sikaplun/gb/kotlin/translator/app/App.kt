package com.sikaplun.gb.kotlin.translator.app

import android.app.Application
import com.sikaplun.gb.kotlin.translator.di.module.appModule
import com.sikaplun.gb.kotlin.translator.di.module.dataModule
import com.sikaplun.gb.kotlin.translator.di.module.retrofitModule
import com.sikaplun.gb.kotlin.translator.room.AppDataBase
import com.sikaplun.gb.kotlin.translator.room.DataModelLocalRepository
import com.sikaplun.gb.kotlin.translator.room.DataModelLocalRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {

    private lateinit var dataBase: AppDataBase
    lateinit var repository: DataModelLocalRepository

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            androidLogger(level = Level.DEBUG)
            modules(listOf(appModule, dataModule, retrofitModule))
        }

        dataBase = AppDataBase.buildDataBase(applicationContext, DATABASE_NAME)
        repository = DataModelLocalRepositoryImpl(dataBase.dataModelLocalDao())
    }

    companion object{
        private const val DATABASE_NAME = "app_database.db"
    }
}