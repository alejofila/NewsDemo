package com.alejofila.data.repository


import com.alejofila.data.api.NewsApi
import com.alejofila.data.db.NewsDao
import com.alejofila.data.file.NewsCsvParser
import com.alejofila.data.mapper.NewsEntityMapper
import com.alejofila.data.utils.ConnectivityManagerWrapper
import com.example.domain.NewsRepository
import com.example.domain.model.News
import io.reactivex.Single

class NewsRepositoryImpl(
        private val newsApi: NewsApi,
        private val newsDao: NewsDao,
        private val connectivityWrapper: ConnectivityManagerWrapper,
        private val newsCsvParser: NewsCsvParser) : NewsRepository {
    override fun getAllNews(): Single<List<News>> {

        return if (connectivityWrapper.isConnectedToNetwork()) {
            newsApi.getNewsFile()
                    .map { newsCsvParser.parseCsvFile(it.byteStream()) }
                    .doAfterSuccess { newsDao.insertAll(it) }
                    .flattenAsObservable { it }
                    .map { NewsEntityMapper.fromEntityToDomain(it) }
                    .toList()
        } else {
            newsDao.getAllnews()
                    .flattenAsObservable { it }
                    .map { NewsEntityMapper.fromEntityToDomain(it) }
                    .toList()
        }

    }


}