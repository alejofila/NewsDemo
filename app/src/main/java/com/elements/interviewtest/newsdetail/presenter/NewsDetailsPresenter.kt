package com.elements.interviewtest.newsdetail.presenter

import com.elements.interviewtest.common.presenter.BasePresenter
import com.elements.interviewtest.common.uimodel.NewsUiModel
import io.reactivex.Scheduler

class NewsListPresenter(val view: NewsDetailView,
                        private val newsUiModel: NewsUiModel,
                        mainScheduler: Scheduler,
                        backgroundScheduler: Scheduler) : BasePresenter(mainScheduler,backgroundScheduler) {
    override fun onStart() {
        showNewsDetails(newsUiModel)
    }
    fun showNewsDetails(newsUiModel: NewsUiModel){
        view.drawNewDetails(newsUiModel)
    }

}
interface NewsDetailView{
    fun drawNewDetails(newsUiModel: NewsUiModel)
}