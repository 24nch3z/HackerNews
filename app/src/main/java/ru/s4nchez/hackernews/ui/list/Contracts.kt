package ru.s4nchez.hackernews.ui.list

import android.support.annotation.StringRes
import android.support.v7.widget.RecyclerView
import com.arellomobile.mvp.MvpView

interface ContractPresenter {
    fun handleOnScrollListener(manager: RecyclerView.LayoutManager?)
}

interface ContractView : MvpView {
    fun initAdapter(items: ArrayList<Any>)
    fun showHideProgressBar(flag: Boolean)
    fun showHideEmptyListView(flag: Boolean)
    fun updateItems(items: ArrayList<Any>)
    fun showToast(@StringRes id: Int)
}