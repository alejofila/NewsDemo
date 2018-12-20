package com.elements.interviewtest.di

import com.element.data.api.NewsApi
import com.element.data.api.NewsService
import com.element.data.db.AppDatabase
import com.element.data.db.NewsDao
import com.element.data.file.NewsCsvParser
import com.element.data.file.NewsCsvParserImpl
import com.element.data.repository.NewsRepositoryImpl
import com.element.data.utils.ConnectivityManagerWrapper
import com.element.data.utils.ConnectivityManagerWrapperImpl
import com.elements.interviewtest.browseNews.presenter.NewsListPresenter
import com.elements.interviewtest.di.Constants.BACKGROUND_SCHEDULER
import com.elements.interviewtest.di.Constants.CSV_HEADERS
import com.elements.interviewtest.di.Constants.MAIN_SCHEDULER
import com.elements.interviewtest.newsdetail.presenter.NewsDetailPresenter
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
    val CSV_HEADERS = listOf<String>("title","description","image")
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
