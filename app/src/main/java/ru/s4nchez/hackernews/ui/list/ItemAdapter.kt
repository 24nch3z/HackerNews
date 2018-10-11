package ru.s4nchez.hackernews.ui.list

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import ru.s4nchez.hackernews.R
import ru.s4nchez.hackernews.data.entities.Item

class ItemAdapter(val items: ArrayList<Item>) : RecyclerView.Adapter<ItemHolder>() {

    fun updateItems(items: List<Item>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_item, parent, false)
        return ItemHolder(view)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ItemHolder, pos: Int) = holder.bind(items[pos])
}