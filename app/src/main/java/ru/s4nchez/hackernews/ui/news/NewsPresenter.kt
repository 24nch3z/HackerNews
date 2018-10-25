package ru.s4nchez.hackernews.ui.news

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.observers.DisposableSingleObserver
import ru.s4nchez.hackernews.R
import ru.s4nchez.hackernews.data.entities.NewsItem
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
    private var isFirstPage = true

    init {
        if (!isLoading) {
            viewState.showHideProgressBar(true)
            loadIdsInteractor.execute(LoadIdsObserver())
        }
    }

    fun loadNextPage() {
        if (isLoading) return
        isLoading = true
        viewState.setListLoading(true)
        loadNextPageInteractor.execute(LoadNextPageObserver())
    }

    override fun onDestroy() {
        super.onDestroy()
        loadIdsInteractor.dispose()
        loadNextPageInteractor.dispose()
    }

    private inner class LoadIdsObserver : DisposableSingleObserver<Boolean>() {

        override fun onSuccess(result: Boolean) {
            viewState.showHideEmptyListView(false)
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

    private inner class LoadNextPageObserver : DisposableSingleObserver<List<NewsItem>>() {

        override fun onSuccess(newItems: List<NewsItem>) {
            stopLoading()
            isFirstPage = false
            viewState.updateItems(ArrayList(newItems))
        }

        override fun onError(e: Throwable) {
            Timber.e(e)
            if (isFirstPage) {
                viewState.showHideEmptyListView(true)
            }
            stopLoading()
//            viewState.showToast(R.string.no_connection)
        }

        private fun stopLoading() {
            isLoading = false
            viewState.showHideProgressBar(false)
            viewState.setListLoading(false)
        }
    }
}