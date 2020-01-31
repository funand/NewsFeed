package com.example.newsfeed.UI.NewsDataAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.newsfeed.R
import com.example.newsfeed.UI.Models.Articles
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.news_items.view.*

class DataAdapter(private val dataset: List<Articles>) :
    RecyclerView.Adapter<DataAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.news_items,
                parent,
                false
            ) as LinearLayout
        )
    }

    override fun getItemCount() = dataset.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataset.get(position))
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(articles: Articles) {
            itemView.author.text = articles.author
            itemView.title.text = articles.title
            itemView.description.text = articles.description
            itemView.url.text = articles.url
            Picasso.get().load(articles.urlToImage).into(itemView.urltoimage)
//            itemView.pub
        }
    }
}