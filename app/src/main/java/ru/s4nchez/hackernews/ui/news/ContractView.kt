package ru.s4nchez.hackernews.ui.news

import android.support.annotation.StringRes
import com.arellomobile.mvp.MvpView

interface ContractView : MvpView {
    fun showHideProgressBar(flag: Boolean)
    fun showHideEmptyListView(flag: Boolean)
    fun updateItems(items: ArrayList<Any>)
    fun showToast(@StringRes id: Int)
    fun setListLoading(flag: Boolean)
}