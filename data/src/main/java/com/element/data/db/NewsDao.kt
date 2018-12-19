package com.element.data.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.element.data.model.NewsEntity
import io.reactivex.Single



@Dao
interface NewsDao {
    @Query("SELECT * FROM news")
    fun getAllnews(): Single<List<NewsEntity>>

    /*
    @Query("SELECT * FROM news WHERE shop_id == :shop_id")
    fun getShopById(shop_id: String): Single<NewsEntity>
    */

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg news: NewsEntity)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(news: List<NewsEntity>)
}