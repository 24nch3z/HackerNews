package ru.s4nchez.hackernews.ui.list

import android.annotation.SuppressLint
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.s4nchez.hackernews.data.entities.Item
import ru.s4nchez.hackernews.interactors.NewsInteractor
import javax.inject.Inject

@InjectViewState
class ListPresenter @Inject constructor(
        private val interactor: NewsInteractor
) : MvpPresenter<ContractView>(), ContractPresenter {

    private val PAGE_SIZE = 20
    private val ids = ArrayList<Int>()
    private val items = ArrayList<Item>()
    private var isLoading = false

    init {
        viewState.initAdapter(items)
        if (!isLoading && ids.isEmpty()) loadIds()
    }

    @SuppressLint("CheckResult")
    private fun loadIds() {
        viewState.showHideProgressBar(true)
        interactor.getNewStories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    ids.addAll(it)
                    loadNextPage()
                }, {
                    Log.e("", it.message)
                    isLoading = false
                    viewState.showHideEmptyListView(true)
                    viewState.showHideProgressBar(false)
                })
    }

    @SuppressLint("CheckResult")
    private fun loadItemsByIds(ids: Array<Int>) {
        isLoading = true
        interactor.getItems(ids)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    isLoading = false
                    items.addAll(it)
                    viewState.updateItems()
                    viewState.showHideProgressBar(false)
                }, {
                    Log.e("", it.message)
                    isLoading = false
                    viewState.showHideProgressBar(false)
                })
    }

    private fun loadNextPage() {
        if (isLoading) return
        val pageSize = Math.min(ids.size - items.size, PAGE_SIZE)
        var position = items.size
        val ids = Array(pageSize) { i -> ids[position++] }
        loadItemsByIds(ids)
    }

    override fun handleOnScrollListener(manager: RecyclerView.LayoutManager?) {
        if (manager == null) return

        val visibleItemCount = manager.childCount
        val totalItemCount = manager.itemCount
        val firstVisibleItemPosition = (manager as LinearLayoutManager)
                .findFirstVisibleItemPosition()

        if (visibleItemCount + firstVisibleItemPosition >= totalItemCount &&
                firstVisibleItemPosition >= 0 && totalItemCount >= PAGE_SIZE) {
            loadNextPage()
        }
    }
}