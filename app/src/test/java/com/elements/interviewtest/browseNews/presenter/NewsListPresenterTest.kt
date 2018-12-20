package com.elements.interviewtest.browseNews.presenter


import com.elements.interviewtest.browseNews.anything
import com.example.domain.NewsRepository
import com.example.domain.model.News
import com.example.domain.usecases.GetNewsUseCase
import io.reactivex.Single
import io.reactivex.schedulers.TestScheduler
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.internal.verification.VerificationModeFactory
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class NewsListPresenterTest{

    @Mock
    lateinit var view :NewsListView

    @Mock
    lateinit var repository: NewsRepository

    private val testScheduler = TestScheduler()
    private lateinit var presenter: NewsListPresenter

    @Before
    fun setup(){
        presenter = NewsListPresenter(GetNewsUseCase(repository),testScheduler,testScheduler)
        presenter.view = view

    }
    @Test
    fun `test zero news`(){
        val singleEmptyList = Single.just(emptyList<News>())
        Mockito.`when`(repository.getAllNews()).thenReturn(singleEmptyList)
        presenter.onStart()
        testScheduler.triggerActions()
        Mockito.verify(view,VerificationModeFactory.times(1)).showLoadingState()
        Mockito.verify(view, VerificationModeFactory.times(1)).showNoNewsMessage()

    }


    @Test
    fun `test single news`(){
        val newsMock = News("","","")
        val news = listOf(newsMock)
        val singleNews = Single.just(news)
        Mockito.`when`(repository.getAllNews()).thenReturn(singleNews)
        presenter.onStart()
        testScheduler.triggerActions()
        Mockito.verify(view,VerificationModeFactory.times(1)).showLoadingState()
        Mockito.verify(view,VerificationModeFactory.times(1)).showNewsList(anything())
    }
    @Test
    fun `test multiple news`(){
        val newsMock = News("","","")
        val newsMock2 = News("abc","bc","asda")
        val newsMock3 = News("abcd","asd","asd")

        val news = listOf(newsMock,newsMock2,newsMock3)
        val singleNews = Single.just(news)
        Mockito.`when`(repository.getAllNews()).thenReturn(singleNews)
        presenter.onStart()
        testScheduler.triggerActions()
        Mockito.verify(view,VerificationModeFactory.times(1)).showLoadingState()
        Mockito.verify(view,VerificationModeFactory.times(1)).showNewsList(anything())
    }


}