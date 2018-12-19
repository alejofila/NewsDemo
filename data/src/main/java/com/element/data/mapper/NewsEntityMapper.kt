package com.element.data.mapper

import com.element.data.model.NewsEntity
import com.example.domain.model.News


object NewsEntityMapper {
    fun fromEntityToDomain(newsEntity: NewsEntity): News {
        with(newsEntity) {
            return News(title,
                    description,
                    image
            )

        }
    }
}