package com.example.dictionary.datas

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.dictionary.models.Word

@Database(entities = [Word::class], version = 1)
abstract class DictionaryDatabase : RoomDatabase() {
    abstract fun dictionaryDAO(): DictionaryDAO

    companion object {
        private var instance: DictionaryDatabase? = null

        fun getInstance(context: Context): DictionaryDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    DictionaryDatabase::class.java,
                    "my_db"
                )
                    .createFromAsset("vt.db")
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return instance!!
        }
    }
}