package com.example.domain.usecases

import com.example.domain.NewsRepository
import com.example.domain.model.News
import io.reactivex.Single


class GetNewsUseCase(private val newsRepository: NewsRepository){
    operator fun invoke (): Single<List<News>> {
        return newsRepository.getAllNews()

    }
}