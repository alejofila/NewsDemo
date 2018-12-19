package com.elements.interviewtest.browseNews.presenter


import com.elements.interviewtest.common.NewsMapper
import com.elements.interviewtest.common.presenter.BasePresenter
import com.elements.interviewtest.common.uimodel.NewsUiModel
import com.example.domain.usecases.GetNewsUseCase
import io.reactivex.Scheduler

class NewsListPresenter(val view: NewsListView,
                        private val getNewsUseCase: GetNewsUseCase,
                        mainScheduler: Scheduler,
                        backgroundScheduler: Scheduler) : BasePresenter(mainScheduler,backgroundScheduler) {

    override fun onStart(){
        getNewsList()
    }
    fun getNewsList() {
        view.showLoadingState()
        disposableBag?.add(getNewsUseCase()
                .flattenAsObservable { it }
                .map { news-> NewsMapper.fromDomainToUiModel(news) }
                .toList()
                .map { it.toMutableList() }
                .subscribeOn(backgroundScheduler)
                .observeOn(mainScheduler)
                .subscribe { news ->
                    if(news.isNotEmpty()){
                        view.hideLoadingState()
                        view.showNewsList(news)
                    }
                    else{
                        view.hideLoadingState()
                        view.showNoNewsMessage()
                    }
                })
    }

}

interface NewsListView {
    fun hideLoadingState()
    fun showLoadingState()
    fun showNewsList(list: MutableList<NewsUiModel>)
    fun showNoNewsMessage()
}

