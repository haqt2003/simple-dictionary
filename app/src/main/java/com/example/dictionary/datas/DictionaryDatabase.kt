package com.example.dictionary.datas

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.dictionary.models.Word

@Database(entities = [Word::class], version = 1)
abstract class DictionaryDatabase : RoomDatabase() {
    abstract fun dictionaryDAO(): DictionaryDAO
}