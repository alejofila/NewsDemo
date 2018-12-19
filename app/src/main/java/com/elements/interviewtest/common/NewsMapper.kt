package com.elements.interviewtest.common

import com.elements.interviewtest.common.uimodel.NewsUiModel
import com.example.domain.model.News

object NewsMapper {
    fun fromDomainToUiModel(news: News): NewsUiModel {
        with(news) {
            return NewsUiModel(title,
                    description,
                    image
            )
        }
    }
}