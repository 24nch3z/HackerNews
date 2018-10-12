package ru.s4nchez.hackernews.ui.list

import android.support.v7.widget.RecyclerView
import com.arellomobile.mvp.MvpView
import ru.s4nchez.hackernews.data.entities.Item

interface ContractPresenter {
    fun handleOnScrollListener(manager: RecyclerView.LayoutManager?)
}

interface ContractView : MvpView {
    fun initAdapter(items: ArrayList<Item>)
    fun showHideProgressBar(flag: Boolean)
    fun showHideEmptyListView(flag: Boolean)
    fun updateItems()
}