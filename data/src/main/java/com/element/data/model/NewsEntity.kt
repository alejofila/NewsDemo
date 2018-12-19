package com.element.data.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

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

