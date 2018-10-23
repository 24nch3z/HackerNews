package ru.s4nchez.hackernews.ui.list

import android.annotation.SuppressLint
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import ru.s4nchez.hackernews.R
import ru.s4nchez.hackernews.interactors.LoadIdsInteractor
import ru.s4nchez.hackernews.interactors.NewsInteractor
import timber.log.Timber
import javax.inject.Inject

@InjectViewState
class ListPresenter @Inject constructor(
        private val interactor: NewsInteractor,
        private val loadIdsInteractor: LoadIdsInteractor
) : MvpPresenter<ContractView>(), ContractPresenter {

    private val PROGRESSBAR_ITEM_TAG = "PROGRESSBAR_ITEM_TAG"
    private val PAGE_SIZE = 10
    private val ids = ArrayList<Int>()
    private val items = ArrayList<Any>()
    private var isLoading = false

    init {
        viewState.initAdapter(items)
//        if (!isLoading && ids.isEmpty()) loadIds()
        if (!isLoading && ids.isEmpty()) {
            viewState.showHideProgressBar(true)
            loadIdsInteractor.execute(LoadIdsObserver(), 1)
        }
    }

//    @SuppressLint("CheckResult")
//    private fun loadIds() {
//        viewState.showHideProgressBar(true)
//        interactor.getNewStories()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe({
//                    ids.addAll(it)
//                    loadNextPage()
//                }, {
//                    Timber.e(it)
//                    isLoading = false
//                    viewState.showHideEmptyListView(true)
//                    viewState.showHideProgressBar(false)
//                    viewState.showToast(R.string.no_connection)
//                })
//    }

    private fun setProgressBarItem() {
        if (!items.isEmpty()) {
            items.add(PROGRESSBAR_ITEM_TAG)
            viewState.updateItems(items)
        }
    }

    private fun removeProgressBarItem() {
        items.remove(PROGRESSBAR_ITEM_TAG)
        viewState.updateItems(items)
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
                    viewState.updateItems(items)
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

    override fun loadNextPage() {
        if (isLoading) return
        val pageSize = Math.min(ids.size - items.size, PAGE_SIZE)
        var position = items.size
        val ids = Array(pageSize) { i -> ids[position++] }
        loadItemsByIds(ids)
    }

    private inner class LoadIdsObserver : DisposableSingleObserver<List<Int>>() {

        override fun onSuccess(t: List<Int>) {
            ids.addAll(t)
            loadNextPage()
        }

        override fun onError(e: Throwable) {
            Timber.e(e)
            isLoading = false
            viewState.showHideEmptyListView(true)
            viewState.showHideProgressBar(false)
            viewState.showToast(R.string.no_connection)
        }
    }
}