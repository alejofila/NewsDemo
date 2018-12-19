package com.elements.interviewtest.browseNews.fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import com.elements.interviewtest.R
import com.elements.interviewtest.browseNews.adapter.NewsAdapter
import com.elements.interviewtest.browseNews.presenter.NewsListPresenter
import com.elements.interviewtest.browseNews.presenter.NewsListView
import com.elements.interviewtest.common.activity.FragmentNavigationCallback
import com.elements.interviewtest.common.dependency.NewsRepositoryFactory
import com.elements.interviewtest.common.uimodel.NewsUiModel
import com.elements.interviewtest.newsdetail.fragment.NewsDetailFragment
import com.example.domain.usecases.GetNewsUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class NewsListFragment : Fragment(), NewsListView {

    private lateinit var contentLoadingProgressBar: ProgressBar
    private lateinit var newsRecyclerView: RecyclerView
    private lateinit var adapter: NewsAdapter
    lateinit var fragmentNavigationCallback: FragmentNavigationCallback
    lateinit var newsListPresenter: NewsListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = NewsAdapter { newsClicked ->
            val fragment = NewsDetailFragment.newInstance(newsClicked)
            fragmentNavigationCallback.navigateTo(fragment, true)
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        fragmentNavigationCallback = context as FragmentNavigationCallback
    }

    override fun showNewsList(list: MutableList<NewsUiModel>) {
        newsRecyclerView.visibility = View.VISIBLE
        adapter.newsUiModels = list
    }

    override fun showLoadingState() {
        contentLoadingProgressBar.visibility = View.VISIBLE
    }

    override fun hideLoadingState() {
        contentLoadingProgressBar.visibility = View.GONE
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_news_list, container, false)
        newsRecyclerView = view?.findViewById(R.id.news_recyclerview) as RecyclerView
        contentLoadingProgressBar = view.findViewById(R.id.progress_bar)
        val newsRepository = NewsRepositoryFactory.getNewsRepository()
        // This could be done with a dependency injection tool like dagger
        newsListPresenter = NewsListPresenter(this, GetNewsUseCase(newsRepository),
                AndroidSchedulers.mainThread(), Schedulers.io())
        newsRecyclerView.adapter = adapter
        newsRecyclerView.layoutManager = LinearLayoutManager(activity)
        return view

    }

    override fun onStart() {
        super.onStart()
        newsListPresenter.onStart()
    }

    override fun showNoNewsMessage() {
        Toast.makeText(context, getString(R.string.no_news_error), Toast.LENGTH_SHORT)
                .show()
    }


    override fun onStop() {
        super.onStop()
        newsListPresenter.onStop()

    }

    companion object {
        fun newInstance(): Fragment {
            return NewsListFragment()
        }
    }
}