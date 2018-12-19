package com.elements.interviewtest.newsdetail.fragment

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.support.design.widget.FloatingActionButton
import android.support.transition.TransitionManager
import android.support.v4.app.Fragment
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.elements.interviewtest.R
import com.elements.interviewtest.common.uimodel.NewsUiModel
import com.elements.interviewtest.newsdetail.presenter.NewsDetailView
import com.elements.interviewtest.newsdetail.presenter.NewsListPresenter
import com.squareup.picasso.Picasso
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class NewsDetailFragment : Fragment(), NewsDetailView {


    lateinit var toolbar: Toolbar
    lateinit var constraintRoot: ConstraintLayout
    lateinit var newsTitleView: TextView
    lateinit var newsDescriptionView: TextView
    lateinit var newsImageView: ImageView
    lateinit var newsDetailPresenter: NewsListPresenter
    var fab: FloatingActionButton? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.news_detail_fragment, container, false)
        constraintRoot = rootView.findViewById(R.id.constraint_root)
        newsTitleView = rootView.findViewById(R.id.news_title)
        newsDescriptionView = rootView.findViewById(R.id.news_description)
        newsImageView = rootView.findViewById(R.id.news_image)
        fab = rootView.findViewById(R.id.fab)
        addAnimationOperations()
        val newsUiModel: NewsUiModel? = arguments?.getParcelable(KEY_NEWS)
        newsDetailPresenter = NewsListPresenter(this, newsUiModel!!, AndroidSchedulers.mainThread(), Schedulers.io())
        return rootView
    }

    @SuppressLint("ObsoleteSdkInt")
    private fun addAnimationOperations() {
        var set = false
        val constraint1 = ConstraintSet()
        constraint1.clone(constraintRoot)
        val constraint2 = ConstraintSet()
        val constraintLayout2 = LayoutInflater.from(context).inflate(R.layout.news_detail_fragment_alt,null).findViewById<ConstraintLayout>(R.id.constraint_root)
        constraint2.clone(constraintLayout2)

        fab?.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                TransitionManager.beginDelayedTransition(constraintRoot)
                val constraint = if (set) constraint1 else constraint2
                constraint.applyTo(constraintRoot)
                val fabDrawable = if(set) R.drawable.ic_zoom else R.drawable.ic_zoom_out
                set = !set
                fab?.setImageResource(fabDrawable)

            }
        }

    }

    override fun drawNewDetails(newsUiModel: NewsUiModel) {
        Picasso.get().load(newsUiModel.image)
                .error(R.drawable.ic_image_not_available_x)
                .placeholder(R.drawable.ic_image_not_available_x)
                .into(newsImageView)

        newsTitleView.text = newsUiModel.title
        newsDescriptionView.text = newsUiModel.description
    }

    override fun onStart() {
        super.onStart()
        newsDetailPresenter.onStart()
    }

    companion object {
        private const val KEY_NEWS = "NEWS"
        fun newInstance(newsUiModel: NewsUiModel): NewsDetailFragment {
            val params = Bundle()
            params.putParcelable(KEY_NEWS, newsUiModel)
            val fragment = NewsDetailFragment()
            fragment.arguments = params
            return fragment
        }
    }
}