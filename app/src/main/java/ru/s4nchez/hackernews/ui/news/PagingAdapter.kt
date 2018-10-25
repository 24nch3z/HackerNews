package ru.s4nchez.hackernews.ui.news

import android.annotation.SuppressLint
import android.arch.paging.PagedListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_item.view.*
import ru.s4nchez.hackernews.R
import ru.s4nchez.hackernews.data.entities.NewsItem
import java.text.SimpleDateFormat

class PagingAdapter(
        private val listener: OnItemClickListener
) : PagedListAdapter<NewsItem, PagingAdapter.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_item, parent, false)
        return ViewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }

    companion object {
        @SuppressLint("SimpleDateFormat")
        val dateFormat = SimpleDateFormat("HH:mm:ss  dd.MM.yyyy")

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<NewsItem>() {
            override fun areItemsTheSame(oldItem: NewsItem, newItem: NewsItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: NewsItem, newItem: NewsItem): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }

    class ViewHolder(
            itemView: View,
            private val listener: OnItemClickListener
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(newsItem: NewsItem) {
            with(itemView) {
                score.text = itemView.context.getString(R.string.score, newsItem.score)
                time.text = dateFormat.format((newsItem.time))
                title.text = newsItem.title
                setOnClickListener { listener.onItemClick(newsItem) }
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(newsItem: NewsItem)
    }
}