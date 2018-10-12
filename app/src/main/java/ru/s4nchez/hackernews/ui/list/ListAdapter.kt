package ru.s4nchez.hackernews.ui.list

import com.hannesdorfmann.adapterdelegates3.ListDelegationAdapter

class ListAdapter : ListDelegationAdapter<ArrayList<Any>>() {

    init {
        items = ArrayList()
        delegatesManager
                .addDelegate(ItemAdapterDelegate())
                .addDelegate(ProgressBarAdapterDelegate())
    }

    fun updateItems(newItems: ArrayList<Any>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }
}