package com.element.data.file

import com.element.data.file.NewsCsvParserImpl.Companion.INVALID_URL
import junit.framework.Assert
import org.apache.commons.io.IOUtils
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class NewsCsvFileParserImplTest {
    private val CSV_HEADERS = listOf("title", "description", "image")
    val newsCsvParser = NewsCsvParserImpl(CSV_HEADERS)


    @Before
    fun setUp() {
    }

    @Test
    fun `test  amount of rows`() {
        val csvString = "title,description,image\n" +
                "Item 1,Description 1,http://farm4.staticflickr.com/3764/10438039923_2ef6f68348_c.jpg\n" +
                "Item 2,Description 2,http://www.officialpsds.com/images/stocks/ALLEY-stock1502.jpg\n" +
                "Item 3,Description 3,https://www.google.com\n" +
                "Item 4,\"Description 4, <i>extra info</i>\",\n" +
                "Item 5,Description 5,http://ladymanson.com/Goodies/PS%20Tutorials/stock2.jpg\n" +
                "\"Item 6, extra info\",<b>Description 6</b>,http://fc01.deviantart.net/fs17/f/2007/129/7/4/Stock_032__by_enchanted_stock.jpg\n" +
                "Item 7,Description 7,http://www.rubberdragon.com/website-design/44/content/just-in-time-stock.png?1290605562\n" +
                "This is a longer description that should wrap arround or broken of with ellipsis,Description 8,http://www.gwp.co.uk/wp-content/uploads/2012/10/jit-case-delivery1.jpg\n" +
                "Item 9,Description 9,http://fc00.deviantart.net/fs70/i/2011/217/a/0/field_panorama_stock_5_by_f3rd4-d45kgfp.jpg\n" +
                "\"Item 10\n" +
                "Extra line 1\n" +
                "Extra line 2\n" +
                "Extra line 3\",Description 10,https://www.google.nl\n" +
                "Item 11,Description 11,\n" +
                "Item 12,Description 12,http://comps.canstockphoto.com/can-stock-photo_csp9177473.jpg\n" +
                "Item 13,Description 13,http://farm4.staticflickr.com/3770/10379608353_cb362676b_b.jpg\n" +
                "\"Item 14, \"\"extra data\"\"\",Description 14,http://www.rubberdragon.com/website-design/44/content/just-in-time-stock.png?1290605562\n" +
                "Item 15,Description 15,\n" +
                "Item 16,Description 16,http://farm4.staticflickr.com/3789/10437199943_8f6476cef1.jpg\n" +
                "Item 17,Description 17,\n" +
                "Item 18,Description 18,http://comps.canstockphoto.com/can-stock-photo_csp9177473.jpg\n" +
                "Item 19,Description 19,\"https://www.google.nl/url?sa=i&rct=j&q=&esrc=s&source=images&cd=&cad=rja&docid=vp47OjPgb8jYjM&tbnid=mB4KC2XedUiDgM:&ved=0CAUQjRw&url=http%3A%2F%2Fwww.naturestocklibrary.com%2F&ei=O8pjUs3_MIbI0QXUnIDIAw&bvm=bv.54934254,d.d2k&psig=AFQjCNHp_WiQTk5MfZ8JliMxj6JYTc206w&ust=1382357938513870\"\n" +
                "Item 20,Description 20,http://www.officialpsds.com/images/stocks/ALLEY-stock1502.jpg\n" +
                "Item 21,Description 21,https://www.google.com\n" +
                "Item 22,Description 22,\n" +
                "Item 23,Description 23,http://comps.canstockphoto.com/can-stock-photo_csp3928973.jpg\n" +
                "Item 24,Description 24,"
        val newsEntityList = newsCsvParser.parseCsvFile(IOUtils.toInputStream(csvString))
        Assert.assertEquals(24, newsEntityList.size)
    }

    @Test
    fun `test content of rows`() {
        val csvString = "title,description,image\n" +
                "Item 1,Description 1,http://farm4.staticflickr.com/3764/10438039923_2ef6f68348_c.jpg\n" +
                "Item 2,Description 2,http://www.officialpsds.com/images/stocks/ALLEY-stock1502.jpg\n"
        val newsEntityList = newsCsvParser.parseCsvFile(IOUtils.toInputStream(csvString))
        val news1 = newsEntityList[0]
        val news2 = newsEntityList[1]
        Assert.assertEquals("Item 1", news1.title)
        Assert.assertEquals("Description 1", news1.description)
        Assert.assertEquals("http://farm4.staticflickr.com/3764/10438039923_2ef6f68348_c.jpg", news1.image)
        Assert.assertEquals("Item 2", news2.title)
        Assert.assertEquals("Description 2", news2.description)
        Assert.assertEquals("http://www.officialpsds.com/images/stocks/ALLEY-stock1502.jpg", news2.image)

    }

    @Test
    fun `test row with multiple lines`() {
        val csvString = "title,description,image\n" +
                "\"Item 10\n" +
                "Extra line 1\n" +
                "Extra line 2\n" +
                "Extra line 3\",Description 10,https://www.google.nl\n"
        val newsList = newsCsvParser.parseCsvFile(IOUtils.toInputStream(csvString))
        Assert.assertEquals(1,newsList.size)
    }
    @Test
    fun `test row with no image`() {
        val csvString = "title,description,image\n" +
                "Item 10,Description 10,\n"
        val newsList = newsCsvParser.parseCsvFile(IOUtils.toInputStream(csvString))
        val news = newsList[0]
        Assert.assertEquals("Item 10",news.title)
        Assert.assertEquals("Description 10",news.description)
        Assert.assertEquals(INVALID_URL,news.image)
    }


}