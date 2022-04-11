package com.sikaplun.gb.kotlin.translator.room


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [DataModelLocal::class], version = 1
)
abstract class AppDataBase : RoomDatabase() {

    abstract fun dataModelLocalDao(): DataModelLocalDao

    companion object{
        fun buildDataBase(context: Context,dbName: String): AppDataBase {
            return Room.databaseBuilder(context, AppDataBase::class.java, dbName).build()
        }
    }
}