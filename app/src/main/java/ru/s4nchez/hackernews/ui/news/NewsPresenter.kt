package ru.s4nchez.hackernews.ui.news

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.observers.DisposableSingleObserver
import ru.s4nchez.hackernews.R
import ru.s4nchez.hackernews.data.entities.Item
import ru.s4nchez.hackernews.interactors.LoadIdsInteractor
import ru.s4nchez.hackernews.interactors.LoadNextPageInteractor
import timber.log.Timber
import javax.inject.Inject

@InjectViewState
class NewsPresenter @Inject constructor(
        private val loadIdsInteractor: LoadIdsInteractor,
        private val loadNextPageInteractor: LoadNextPageInteractor
) : MvpPresenter<ContractView>() {

    private var isLoading = false

    init {
        if (!isLoading) {
            viewState.showHideProgressBar(true)
            loadIdsInteractor.execute(LoadIdsObserver(), LoadIdsInteractor.Params())
        }
    }

    fun loadNextPage() {
        if (isLoading) return
        isLoading = true
        viewState.setListLoading(true)
        loadNextPageInteractor.execute(LoadNextPageObserver(), LoadNextPageInteractor.Params())
    }

    override fun onDestroy() {
        super.onDestroy()
        loadIdsInteractor.dispose()
        loadNextPageInteractor.dispose()
    }

    private inner class LoadIdsObserver : DisposableSingleObserver<Boolean>() {

        override fun onSuccess(result: Boolean) {
            if (result) loadNextPage()
        }

        override fun onError(e: Throwable) {
            Timber.e(e)
            isLoading = false
            viewState.showHideEmptyListView(true)
            viewState.showHideProgressBar(false)
            viewState.showToast(R.string.no_connection)
        }
    }

    private inner class LoadNextPageObserver : DisposableSingleObserver<List<Item>>() {

        override fun onSuccess(newItems: List<Item>) {
            isLoading = false
            viewState.updateItems(ArrayList(newItems))
            viewState.showHideProgressBar(false)
            viewState.setListLoading(false)
        }

        override fun onError(e: Throwable) {
            Timber.e(e)
            isLoading = false
//            if (items.isEmpty()) { // TODO
//                viewState.showHideEmptyListView(true)
//            }
            viewState.showHideProgressBar(false)
            viewState.showToast(R.string.no_connection)
            viewState.setListLoading(false)
        }
    }
}