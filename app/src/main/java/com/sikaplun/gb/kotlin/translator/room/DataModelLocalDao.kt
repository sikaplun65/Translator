package com.sikaplun.gb.kotlin.translator.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DataModelLocalDao {
    @Insert
    suspend fun insert(dataModelLocal: DataModelLocal)

    @Query("select * from ${DataModelLocal.DATA_MODEL_LOCAL_TABLE}")
    suspend fun getAllWords(): List<DataModelLocal>
}