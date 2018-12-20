package com.element.data.file

import com.element.data.model.NewsEntity
import java.io.InputStream

interface NewsCsvParser{
    fun parseCsvFile(inputStream: InputStream) : List<NewsEntity>
}

