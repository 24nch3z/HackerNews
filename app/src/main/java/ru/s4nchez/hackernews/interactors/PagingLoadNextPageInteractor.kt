package ru.s4nchez.hackernews.interactors

import android.arch.paging.PagedList
import android.arch.paging.RxPagedListBuilder
import io.reactivex.Observable
import ru.s4nchez.hackernews.data.PagedNewsItemsDataSourceFactory
import ru.s4nchez.hackernews.data.entities.NewsItem

class PagingLoadNextPageInteractor(
        private val factory: PagedNewsItemsDataSourceFactory
) : ObservableInteractor<PagedList<NewsItem>>() {

    private val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(7)
            .setPageSize(7)
            .setPrefetchDistance(6)
            .build()

    override fun buildObservableInteractor(): Observable<PagedList<NewsItem>> {
        return RxPagedListBuilder(factory, config)
                .buildObservable()
    }
}