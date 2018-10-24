package ru.s4nchez.hackernews.ui.news

import com.hannesdorfmann.adapterdelegates3.ListDelegationAdapter
import ru.s4nchez.hackernews.data.entities.Item

class NewsAdapter(
        listener: OnItemClickListener
) : ListDelegationAdapter<ArrayList<Any>>() {

    private val PROGRESSBAR_ITEM_TAG = "PROGRESSBAR_ITEM_TAG"

    init {
        items = ArrayList()
        delegatesManager
                .addDelegate(ItemAdapterDelegate(listener))
                .addDelegate(ProgressBarAdapterDelegate())
    }

    fun updateItems(newItems: ArrayList<Any>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    fun setProgressBarItem() {
        if (!items.isEmpty()) {
            items.add(PROGRESSBAR_ITEM_TAG)
            notifyItemInserted(items.size - 1)
        }
    }

    fun removeProgressBarItem() {
        items.remove(PROGRESSBAR_ITEM_TAG)
        notifyItemRemoved(items.size - 1)
    }

    interface OnItemClickListener {
        fun onItemClick(item: Item)
    }
}