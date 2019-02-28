package com.alejofila.data.file

import com.alejofila.data.model.NewsEntity
import java.io.InputStream

interface NewsCsvParser{
    fun parseCsvFile(inputStream: InputStream) : List<NewsEntity>
}

