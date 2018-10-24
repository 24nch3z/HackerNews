package ru.s4nchez.hackernews.ui.news

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.hannesdorfmann.adapterdelegates3.AdapterDelegate
import kotlinx.android.synthetic.main.item_item.view.*
import ru.s4nchez.hackernews.R
import ru.s4nchez.hackernews.data.entities.NewsItem
import ru.s4nchez.hackernews.utils.inflate
import java.text.SimpleDateFormat

class ItemAdapterDelegate(
        val listener: NewsAdapter.OnItemClickListener
) : AdapterDelegate<ArrayList<Any>>() {

    companion object {
        @SuppressLint("SimpleDateFormat")
        val dateFormat = SimpleDateFormat("HH:mm:ss  dd.MM.yyyy")
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder =
            ViewHolder(parent.inflate(R.layout.item_item), listener)

    override fun isForViewType(items: ArrayList<Any>, pos: Int): Boolean = items[pos] is NewsItem

    override fun onBindViewHolder(items: ArrayList<Any>, pos: Int, holder: RecyclerView.ViewHolder, payloads: MutableList<Any>) {
        (holder as ViewHolder).bind(items[pos] as NewsItem)
    }

    private inner class ViewHolder(
            itemView: View,
            val listener: NewsAdapter.OnItemClickListener
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
}