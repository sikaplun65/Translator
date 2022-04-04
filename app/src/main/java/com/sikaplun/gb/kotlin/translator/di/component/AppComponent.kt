package com.sikaplun.gb.kotlin.translator.di.component

import com.sikaplun.gb.kotlin.translator.di.module.AppModule
import com.sikaplun.gb.kotlin.translator.di.module.DataModule
import com.sikaplun.gb.kotlin.translator.di.module.RetrofitModule
import com.sikaplun.gb.kotlin.translator.ui.main.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, DataModule::class, RetrofitModule::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)

    @Component.Factory
    interface Factory{
        fun create(@BindsInstance retrofitModule: RetrofitModule): AppComponent
    }
}