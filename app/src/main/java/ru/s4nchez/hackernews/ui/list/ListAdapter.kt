package ru.s4nchez.hackernews.ui.list

import com.hannesdorfmann.adapterdelegates3.ListDelegationAdapter
import ru.s4nchez.hackernews.data.entities.Item

class ListAdapter(
        val listener: OnItemClickListener
) : ListDelegationAdapter<ArrayList<Any>>() {

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

    interface OnItemClickListener {
        fun onItemClick(item: Item)
    }
}