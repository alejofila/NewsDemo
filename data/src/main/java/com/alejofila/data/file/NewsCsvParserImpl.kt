package com.alejofila.data.file

import com.alejofila.data.model.NewsEntity
import de.siegmar.fastcsv.reader.CsvReader
import okhttp3.internal.Util
import org.apache.commons.io.IOUtils
import org.apache.commons.lang3.StringUtils
import java.io.InputStream
import java.io.StringReader

class
NewsCsvParserImpl(private val headers: List<String>) : NewsCsvParser {
    companion object {
        const val INVALID_URL = "INVALID_URL"
    }

    override fun parseCsvFile(inputStream: InputStream): List<NewsEntity> {
        val mutableListOfNews = mutableListOf<NewsEntity>()
        val csvReader = CsvReader()
        csvReader.setContainsHeader(true)
        val csvString = IOUtils.toString(inputStream, Util.UTF_8)
        val stringReader = StringReader(csvString)
        val csvContainer = csvReader.read(stringReader)
        for (row in csvContainer.rows) {
            val title = row.getField(headers[0])
            val description = row.getField(headers[1])
            val image = if (StringUtils.isBlank(row.getField(headers[2]))) INVALID_URL else row.getField(headers[2])
            val newsEntity = NewsEntity(title = title, description = description, image = image)
            mutableListOfNews.add(newsEntity)

        }
        return mutableListOfNews
    }


}
