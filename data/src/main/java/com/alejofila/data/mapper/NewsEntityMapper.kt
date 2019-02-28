package com.alejofila.data.mapper

import com.alejofila.data.model.NewsEntity
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