package com.alejofila.newsdemo.browseNews.adapter

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.alejofila.newsdemo.R
import com.alejofila.newsdemo.common.uimodel.NewsUiModel
import com.squareup.picasso.Picasso


internal class NewsAdapter(val newsClickListener: (NewsUiModel) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var newsUiModels: MutableList<NewsUiModel> = mutableListOf()
        set(value) {
            clearData()
            this.newsUiModels.addAll(value)
            notifyDataSetChanged()
        }
    fun clearData(){
        this.newsUiModels.clear()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return NewsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_news_2, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as NewsViewHolder).bind(newsUiModels[position])
    }

    override fun getItemCount(): Int {
        return newsUiModels.size
    }

    internal inner class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        var newsTitle: TextView = itemView.findViewById(R.id.news_title)
        var newsDescription: TextView = itemView.findViewById(R.id.news_description)
        var newsImage: ImageView = itemView.findViewById(R.id.news_image)

        fun bind(newsUiModel: NewsUiModel) {
            if(newsUiModel.title == "") newsTitle.visibility = View.GONE else newsTitle.text = newsUiModel.title
            if(newsUiModel.description == "") newsDescription.visibility = View.GONE else newsDescription.text =  newsUiModel.description
            itemView.setOnClickListener {
                newsClickListener.invoke(newsUiModel)
            }
            Log.v("","Image path is ${newsUiModel.image}")
            Picasso.get().load(newsUiModel.image)
                    .error(R.drawable.ic_image_not_available_x)
                    .placeholder(R.drawable.ic_image_not_available_x)
                    .fit()
                    .into(newsImage)
        }
    }

}