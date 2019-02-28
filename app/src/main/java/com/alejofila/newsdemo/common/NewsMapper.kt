package com.alejofila.newsdemo.common

import com.alejofila.newsdemo.common.uimodel.NewsUiModel
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