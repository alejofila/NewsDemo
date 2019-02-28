package com.alejofila.newsdemo.newsdetail.presenter

import com.alejofila.newsdemo.common.presenter.BasePresenter
import com.alejofila.newsdemo.common.uimodel.NewsUiModel
import io.reactivex.Scheduler

class NewsDetailPresenter(
                          mainScheduler: Scheduler,
                          backgroundScheduler: Scheduler) : BasePresenter(mainScheduler,backgroundScheduler) {
    lateinit var view: NewsDetailView
    lateinit var newsUiModel: NewsUiModel

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