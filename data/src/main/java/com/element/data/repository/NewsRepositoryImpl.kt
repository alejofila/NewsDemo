package com.element.data.repository


import com.element.data.api.NewsApi
import com.element.data.db.NewsDao

import com.element.data.mapper.NewsEntityMapper
import com.element.data.model.NewsEntity
import com.element.data.utils.ConnectivityManagerWrapper
import com.example.domain.NewsRepository
import com.example.domain.model.News
import io.reactivex.Single
import okhttp3.ResponseBody
import okhttp3.internal.Util
import org.apache.commons.io.IOUtils
import org.apache.commons.lang3.StringUtils

class NewsRepositoryImpl(
        private val newsApi: NewsApi,
        private val newsDao: NewsDao,
        private val connectivityWrapper: ConnectivityManagerWrapper) : NewsRepository {
    override fun getAllNews(): Single<List<News>> {

        return if (connectivityWrapper.isConnectedToNetwork()) {
            newsApi.getNewsFile()
                    .map { parseNewsFile(it) }
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

    private fun parseNewsFile(responseBody: ResponseBody): List<NewsEntity> {
        val inputStream = responseBody.byteStream()
        val result = IOUtils.toString(inputStream, Util.UTF_8)
        val rawLines = result.split("\n")
        val lines  = rawLines.subList(1,rawLines.size)
        val mutableListOfNews = mutableListOf<NewsEntity>()
        lines.forEach {
            val content = it.split(",")
            val title = content.getOrElse(0){
                ""
            }
            val description = content.getOrNull(1)

            val image = if(StringUtils.isBlank(content.getOrNull(2))) "INVALID_URL" else content[2]
            val newsEntity = NewsEntity(title = title, description = description, image = image)
            mutableListOfNews.add(newsEntity)
        }

        return mutableListOfNews

    }

}