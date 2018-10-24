package ru.s4nchez.hackernews.ui.news

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.hannesdorfmann.adapterdelegates3.AdapterDelegate
import kotlinx.android.synthetic.main.item_item.view.*
import ru.s4nchez.hackernews.R
import ru.s4nchez.hackernews.data.entities.Item
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

    override fun isForViewType(items: ArrayList<Any>, pos: Int): Boolean = items[pos] is Item

    override fun onBindViewHolder(items: ArrayList<Any>, pos: Int, holder: RecyclerView.ViewHolder, payloads: MutableList<Any>) {
        (holder as ViewHolder).bind(items[pos] as Item)
    }

    private inner class ViewHolder(
            itemView: View,
            val listener: NewsAdapter.OnItemClickListener
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: Item) {
            with(itemView) {
                score.text = itemView.context.getString(R.string.score, item.score)
                time.text = dateFormat.format((item.time))
                title.text = item.title
                setOnClickListener { listener.onItemClick(item) }
            }
        }
    }
}