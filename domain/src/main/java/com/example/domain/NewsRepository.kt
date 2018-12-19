package com.example.domain

import com.example.domain.model.News
import io.reactivex.Single

interface NewsRepository{
    fun getAllNews() : Single<List<News>>
    //fun getNewsById(newsId: String): Single<News>
}