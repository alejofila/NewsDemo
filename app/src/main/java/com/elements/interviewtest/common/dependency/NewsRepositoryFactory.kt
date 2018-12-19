package com.elements.interviewtest.common.dependency

import com.elements.interviewtest.ElementApp
import com.element.data.api.NewsService
import com.element.data.db.AppDatabase
import com.element.data.repository.NewsRepositoryImpl
import com.element.data.utils.ConnectivityManagerWrapperImpl
import com.example.domain.NewsRepository

/**
 * This was a used as a dependency injection container, it should be done with
 * a tool like dagger
 */

object NewsRepositoryFactory {
    var newsApi =    NewsService.getNewsApi()
    var newsDao = AppDatabase.getInstance(ElementApp.applicationContext()).newsDao()

    fun getNewsRepository(): NewsRepository {
        return NewsRepositoryImpl(
                newsApi,
                newsDao,
                ConnectivityManagerWrapperImpl(ElementApp.applicationContext()))
    }
}


