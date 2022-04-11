package com.sikaplun.gb.kotlin.translator.room

interface DataModelLocalRepository {

    suspend fun insert(dataModelLocal: DataModelLocal)

    suspend fun getAllDataModelLocal(): List<DataModelLocal>

}