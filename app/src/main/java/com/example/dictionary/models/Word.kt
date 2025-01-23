package com.example.dictionary.models

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "han_tu", indices = [
    Index(value = ["pinyin"], name = "h_p"),
    Index(value = ["viet"], name = "h_v"),
    Index(value = ["han"], name = "h_h")
])
data class Word(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    val id: Int? = null,

    @ColumnInfo(name = "han")
    val han: String? = null,

    @ColumnInfo(name = "viet")
    val viet: String? = null,

    @ColumnInfo(name = "pinyin")
    val pinyin: String? = null,

    @ColumnInfo(name = "mean")
    val mean: String? = null
)
