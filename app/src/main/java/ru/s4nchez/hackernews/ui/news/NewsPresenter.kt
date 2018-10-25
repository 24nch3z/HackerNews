package ru.s4nchez.hackernews.ui.news

import android.arch.paging.PagedList
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.observers.DisposableObserver
import io.reactivex.observers.DisposableSingleObserver
import ru.s4nchez.hackernews.R
import ru.s4nchez.hackernews.data.entities.NewsItem
import ru.s4nchez.hackernews.interactors.LoadIdsInteractor
import ru.s4nchez.hackernews.interactors.PagingLoadNextPageInteractor
import timber.log.Timber
import javax.inject.Inject

@InjectViewState
class NewsPresenter @Inject constructor(
        private val loadIdsInteractor: LoadIdsInteractor,
        private val pagingLoadNextPageInteractor: PagingLoadNextPageInteractor
) : MvpPresenter<ContractView>() {

    private var isLoading = false
    private var isFirstPage = true

    fun loadIds() {
        viewState.showHideProgressBar(true)
        loadIdsInteractor.execute(LoadIdsObserver())
    }

    override fun onDestroy() {
        super.onDestroy()
        loadIdsInteractor.dispose()
    }

    private inner class PagingLoadNextPageObserver : DisposableObserver<PagedList<NewsItem>>() {

        override fun onComplete() {}

        override fun onNext(t: PagedList<NewsItem>) {
            viewState.submitList(t)
            viewState.showHideProgressBar(false)
        }

        override fun onError(e: Throwable) {}
    }

    private inner class LoadIdsObserver : DisposableSingleObserver<Boolean>() {

        override fun onSuccess(result: Boolean) {
            viewState.showHideEmptyListView(false)
            if (result) {
                pagingLoadNextPageInteractor.execute(PagingLoadNextPageObserver())
            }
        }

        override fun onError(e: Throwable) {
            Timber.e(e)
            isLoading = false
            viewState.showHideEmptyListView(true)
            viewState.showHideProgressBar(false)
            viewState.showToast(R.string.no_connection)
        }
    }

//    private inner class LoadNextPageObserver : DisposableSingleObserver<List<NewsItem>>() {
//
//        override fun onSuccess(newItems: List<NewsItem>) {
//            stopLoading()
//            isFirstPage = false
//            viewState.updateItems(ArrayList(newItems))
//        }
//
//        override fun onError(e: Throwable) {
//            Timber.e(e)
//            if (isFirstPage) {
//                viewState.showHideEmptyListView(true)
//            }
//            stopLoading()
////            viewState.showToast(R.string.no_connection)
//        }
//
//        private fun stopLoading() {
//            isLoading = false
//            viewState.showHideProgressBar(false)
//            viewState.setListLoading(false)
//        }
//    }
}