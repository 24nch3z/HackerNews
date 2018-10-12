package ru.s4nchez.hackernews.ui.list

import android.annotation.SuppressLint
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.s4nchez.hackernews.R
import ru.s4nchez.hackernews.interactors.NewsInteractor
import timber.log.Timber
import javax.inject.Inject

@InjectViewState
class ListPresenter @Inject constructor(
        private val interactor: NewsInteractor
) : MvpPresenter<ContractView>(), ContractPresenter {

    private val PROGRESSBAR_ITEM_TAG = "PROGRESSBAR_ITEM_TAG"
    private val PAGE_SIZE = 10
    private val ids = ArrayList<Int>()
    private val items = ArrayList<Any>()
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
                    Timber.e(it)
                    isLoading = false
                    viewState.showHideEmptyListView(true)
                    viewState.showHideProgressBar(false)
                    viewState.showToast(R.string.no_connection)
                })
    }

    private fun setProgressBarItem() {
        if (!items.isEmpty()) {
            items.add(PROGRESSBAR_ITEM_TAG)
            viewState.updateItems()
        }
    }

    private fun removeProgressBarItem() {
        items.remove(PROGRESSBAR_ITEM_TAG)
        viewState.updateItems()
    }

    @SuppressLint("CheckResult")
    private fun loadItemsByIds(ids: Array<Int>) {
        if (ids.isEmpty()) return
        isLoading = true
        setProgressBarItem()

        interactor.getItems(ids)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    isLoading = false
                    items.addAll(it)
                    viewState.updateItems()
                    viewState.showHideProgressBar(false)
                    removeProgressBarItem()
                }, {
                    Timber.e(it)
                    isLoading = false
                    if (items.isEmpty()) viewState.showHideEmptyListView(true)
                    viewState.showHideProgressBar(false)
                    viewState.showToast(R.string.no_connection)
                    removeProgressBarItem()
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

        with(manager as LinearLayoutManager) {
            val firstVisibleItemPos = findFirstVisibleItemPosition()
            if (childCount + firstVisibleItemPos >= itemCount && firstVisibleItemPos >= 0) {
                loadNextPage()
            }
        }
    }
}