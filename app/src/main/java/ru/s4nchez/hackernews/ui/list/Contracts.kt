package ru.s4nchez.hackernews.ui.list

import android.support.annotation.StringRes
import com.arellomobile.mvp.MvpView

interface ContractPresenter {
    fun loadNextPage()
}

interface ContractView : MvpView {
    fun initAdapter(items: ArrayList<Any>)
    fun showHideProgressBar(flag: Boolean)
    fun showHideEmptyListView(flag: Boolean)
    fun updateItems(items: ArrayList<Any>)
    fun showToast(@StringRes id: Int)
}