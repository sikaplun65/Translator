package com.sikaplun.gb.kotlin.translator.room

import android.icu.util.Calendar
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sikaplun.gb.kotlin.translator.room.DataModelLocal.Companion.DATA_MODEL_LOCAL_TABLE


@Entity(tableName = DATA_MODEL_LOCAL_TABLE)
data class DataModelLocal(
    @ColumnInfo(name = WORD) var word: String?,
    @ColumnInfo(name = DESCRIPTION) var description: String?
) {
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = NOTE_COLUMN_ID)
    lateinit var id: String

    init {
        generateId()
    }

    private fun generateId() {
        id = Calendar.getInstance().time.time.toString()
    }

    companion object{
        const val DATA_MODEL_LOCAL_TABLE = "dataModelLocal"
        const val NOTE_COLUMN_ID = "id"
        const val WORD = "word"
        const val DESCRIPTION = "description"
    }
}