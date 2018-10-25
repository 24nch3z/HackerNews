package ru.s4nchez.hackernews.ui.news

import android.arch.paging.PagedList
import android.support.annotation.StringRes
import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import ru.s4nchez.hackernews.data.entities.NewsItem

interface ContractView : MvpView {

    fun showHideProgressBar(flag: Boolean)

    fun showHideEmptyListView(flag: Boolean)

    @StateStrategyType(SkipStrategy::class)
    fun showToast(@StringRes id: Int)

    fun submitList(items: PagedList<NewsItem>)
}