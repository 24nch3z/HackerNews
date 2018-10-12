package ru.s4nchez.hackernews.ui.list

import com.hannesdorfmann.adapterdelegates3.ListDelegationAdapter

class ListAdapter(items: ArrayList<Any>) : ListDelegationAdapter<ArrayList<Any>>() {

    init {
        this.items = items
        delegatesManager.addDelegate(ItemAdapterDelegate())
    }

    fun updateItems() {
        notifyDataSetChanged()
    }
}