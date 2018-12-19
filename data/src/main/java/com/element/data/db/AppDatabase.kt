package com.element.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.element.data.model.NewsEntity


@Database(entities = [NewsEntity::class],
        version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun newsDao(): NewsDao


    companion object {
        val DATABASE_NAME = "basic-sample-db"

        fun getInstance(context: Context): AppDatabase {

            return Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, DATABASE_NAME)
                    .build()

        }
    }
}


