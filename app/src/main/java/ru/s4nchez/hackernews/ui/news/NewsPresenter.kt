package ru.s4nchez.hackernews.ui.news

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.observers.DisposableSingleObserver
import ru.s4nchez.hackernews.R
import ru.s4nchez.hackernews.data.entities.Item
import ru.s4nchez.hackernews.interactors.GetItemsInteractor
import ru.s4nchez.hackernews.interactors.LoadIdsInteractor
import timber.log.Timber
import javax.inject.Inject

@InjectViewState
class NewsPresenter @Inject constructor(
        loadIdsInteractor: LoadIdsInteractor,
        private val getItemsInteractor: GetItemsInteractor
) : MvpPresenter<ContractView>() {

    private val PAGE_SIZE = 10
    private val ids = ArrayList<Int>()
    private val items = ArrayList<Any>()
    private var isLoading = false

    init {
        viewState.initAdapter(items)
        if (!isLoading && ids.isEmpty()) {
            viewState.showHideProgressBar(true)
            loadIdsInteractor.execute(LoadIdsObserver(), LoadIdsInteractor.Params())
        }
    }

    fun loadNextPage() {
        if (isLoading) return
        val pageSize = Math.min(ids.size - items.size, PAGE_SIZE)
        var position = items.size
        val ids = Array(pageSize) { i -> ids[position++] }
        isLoading = true
        viewState.setListLoading(true)
        getItemsInteractor.execute(GetItemsObserver(), GetItemsInteractor.Params(ids))
    }

    private inner class LoadIdsObserver : DisposableSingleObserver<List<Int>>() {

        override fun onSuccess(items: List<Int>) {
            ids.addAll(items)
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

    private inner class GetItemsObserver : DisposableSingleObserver<List<Item>>() {

        override fun onSuccess(newItems: List<Item>) {
            isLoading = false
            items.addAll(newItems)
            viewState.updateItems(items)
            viewState.showHideProgressBar(false)
            viewState.setListLoading(false)
        }

        override fun onError(e: Throwable) {
            Timber.e(e)
            isLoading = false
            if (items.isEmpty()) {
                viewState.showHideEmptyListView(true)
            }
            viewState.showHideProgressBar(false)
            viewState.showToast(R.string.no_connection)
            viewState.setListLoading(false)
        }
    }
}