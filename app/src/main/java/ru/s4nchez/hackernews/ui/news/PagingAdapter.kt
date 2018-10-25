package ru.s4nchez.hackernews.ui.news

import android.arch.paging.PagedListAdapter
import android.support.v7.util.DiffUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import ru.s4nchez.hackernews.R
import ru.s4nchez.hackernews.data.entities.NewsItem

class PagingAdapter(
        val listener: NewsAdapter.OnItemClickListener
) : PagedListAdapter<NewsItem, ItemAdapterDelegate.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemAdapterDelegate.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_item, parent, false)
        return ItemAdapterDelegate.ViewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: ItemAdapterDelegate.ViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<NewsItem>() {
            override fun areItemsTheSame(oldItem: NewsItem, newItem: NewsItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: NewsItem, newItem: NewsItem): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }

}