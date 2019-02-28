package com.alejofila.newsdemo.di

import com.alejofila.data.api.NewsApi
import com.alejofila.data.api.NewsService
import com.alejofila.data.db.AppDatabase
import com.alejofila.data.db.NewsDao
import com.alejofila.data.file.NewsCsvParser
import com.alejofila.data.file.NewsCsvParserImpl
import com.alejofila.data.repository.NewsRepositoryImpl
import com.alejofila.data.utils.ConnectivityManagerWrapper
import com.alejofila.data.utils.ConnectivityManagerWrapperImpl
import com.alejofila.newsdemo.browseNews.presenter.NewsListPresenter
import com.alejofila.newsdemo.di.Constants.BACKGROUND_SCHEDULER
import com.alejofila.newsdemo.di.Constants.CSV_HEADERS
import com.alejofila.newsdemo.di.Constants.MAIN_SCHEDULER
import com.alejofila.newsdemo.newsdetail.presenter.NewsDetailPresenter
import com.example.domain.NewsRepository
import com.example.domain.usecases.GetNewsUseCase
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module

object Constants{
    const val MAIN_SCHEDULER = "mainThreadScheduler"
    const val BACKGROUND_SCHEDULER ="backgroundThreadScheduler"
    val CSV_HEADERS = listOf("title","description","image")
}
val newsModule = module{

    single<ConnectivityManagerWrapper>{ ConnectivityManagerWrapperImpl(androidContext())}

    single<NewsDao> { AppDatabase.getInstance(androidContext()).newsDao()}
    single<NewsApi>{ NewsService.getNewsApi()}
    single<NewsRepository>{ NewsRepositoryImpl(get(),get(),get(),get()) }
    single<GetNewsUseCase>{ GetNewsUseCase(get()) }
    single<Scheduler>(MAIN_SCHEDULER){AndroidSchedulers.mainThread()}
    single<Scheduler>(BACKGROUND_SCHEDULER){ Schedulers.io()}
    factory<NewsListPresenter>{ NewsListPresenter(get(),get(MAIN_SCHEDULER),get(BACKGROUND_SCHEDULER)) }
    factory<NewsDetailPresenter>{ NewsDetailPresenter(get(MAIN_SCHEDULER),get(BACKGROUND_SCHEDULER))}
    single<NewsCsvParser>{ NewsCsvParserImpl(CSV_HEADERS)}



}
