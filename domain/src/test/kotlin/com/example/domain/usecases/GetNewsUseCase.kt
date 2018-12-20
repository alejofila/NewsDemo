package com.example.domain.usecases

import com.example.domain.NewsRepository
import com.example.domain.model.News
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetNewsUseCaseTest {

    @Mock
    lateinit var newsRepository: NewsRepository
    lateinit var getNewsUseCase: GetNewsUseCase

    @Before
    fun setUp() {
        getNewsUseCase = GetNewsUseCase(newsRepository)
    }

    @Test
    fun `test  getting  empty  list  of  news `(){
        Mockito.`when`(newsRepository.getAllNews()).thenReturn(Single.just(emptyList()))
        val testObserver = getNewsUseCase().test()
        testObserver.assertValue{it.isEmpty()}

    }
    @Test
    fun `test getting list of multiple news`() {
        val news = mock(News::class.java)
        val news2 =  mock(News::class.java)
        val list = listOf(news, news2)
        Mockito.`when`(newsRepository.getAllNews()).thenReturn(Single.just(list))
        val testObserver = getNewsUseCase().test()
        testObserver.assertValue{it==list}

    }
}