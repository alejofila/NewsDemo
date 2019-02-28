package com.alejofila.data.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "news")
class NewsEntity(

        @PrimaryKey
        @ColumnInfo(name = "title")
        var title: String,

        @ColumnInfo(name = "description")
        var description: String?,

        @ColumnInfo(name = "image")
        var image: String?
)

