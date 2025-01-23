package com.example.dictionary.datas

import androidx.room.Dao
import androidx.room.Query
import com.example.dictionary.models.Word

@Dao
interface DictionaryDAO {
    @Query("SELECT * FROM han_tu")
    suspend fun getAll(): List<Word>

    @Query("""
        SELECT * FROM han_tu
        WHERE han LIKE '%' || :str || '%' 
       OR viet LIKE '%' || :str || '%' 
       OR pinyin LIKE '%' || :str || '%' 
       OR mean LIKE '%' || :str || '%'
       """)
    suspend fun findByString(str: String): List<Word>

    @Query("SELECT * FROM han_tu WHERE _id = :id")
    suspend fun findByID(id: Int): Word
}