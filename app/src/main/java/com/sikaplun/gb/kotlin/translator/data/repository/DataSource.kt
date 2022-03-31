package com.sikaplun.gb.kotlin.translator.data.repository

import io.reactivex.rxjava3.core.Observable

interface DataSource<T> {
    fun getData(): Observable<T>
}