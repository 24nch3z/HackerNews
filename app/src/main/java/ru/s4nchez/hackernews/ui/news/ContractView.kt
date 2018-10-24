package ru.s4nchez.hackernews.ui.news

import android.support.annotation.StringRes
import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

interface ContractView : MvpView {
    fun showHideProgressBar(flag: Boolean)
    fun showHideEmptyListView(flag: Boolean)
    fun updateItems(items: ArrayList<Any>)
    @StateStrategyType(SkipStrategy::class) fun showToast(@StringRes id: Int)
    fun setListLoading(flag: Boolean)
}