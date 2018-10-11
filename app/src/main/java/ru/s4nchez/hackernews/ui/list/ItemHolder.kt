package ru.s4nchez.hackernews.ui.list

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.item_item.view.*
import ru.s4nchez.hackernews.R
import ru.s4nchez.hackernews.data.entities.Item
import java.text.SimpleDateFormat

class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    companion object {
        @SuppressLint("SimpleDateFormat")
        val dateFormat = SimpleDateFormat("HH:mm:ss  dd.MM.yyyy")
    }

    fun bind(item: Item) {
        with(itemView) {
            score.text = itemView.context.getString(R.string.score, item.score)
            time.text = dateFormat.format((item.time))
            title.text = item.title
        }
    }
}