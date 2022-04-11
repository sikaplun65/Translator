package com.sikaplun.gb.kotlin.translator.room

class DataModelLocalRepositoryImpl(private val dao: DataModelLocalDao): DataModelLocalRepository {
    override suspend fun insert(dataModelLocal: DataModelLocal) {
        dao.insert(dataModelLocal)
    }

    override suspend fun getAllDataModelLocal(): List<DataModelLocal> = dao.getAllWords()

}