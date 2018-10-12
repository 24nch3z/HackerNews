package ru.s4nchez.hackernews.ui.list

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.hannesdorfmann.adapterdelegates3.AdapterDelegate
import ru.s4nchez.hackernews.R
import ru.s4nchez.hackernews.utils.inflate

class ProgressBarAdapterDelegate : AdapterDelegate<ArrayList<Any>>() {

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder =
            ViewHolder(parent.inflate(R.layout.item_progress_bar))

    override fun isForViewType(items: ArrayList<Any>, pos: Int) = items[pos] is String

    override fun onBindViewHolder(items: ArrayList<Any>, position: Int, holder: RecyclerView.ViewHolder, payloads: MutableList<Any>) {}

    private inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}