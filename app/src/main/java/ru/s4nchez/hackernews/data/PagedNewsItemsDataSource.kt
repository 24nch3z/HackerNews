package ru.s4nchez.hackernews.data

import android.arch.paging.PositionalDataSource
import ru.s4nchez.hackernews.data.entities.NewsItem
import ru.s4nchez.hackernews.data.repositories.NewsRepository

class PagedNewsItemsDataSource(
        private val repository: NewsRepository
) : PositionalDataSource<NewsItem>() {

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<NewsItem>) {
        val disp = repository.loadNextPage(params.startPosition, params.loadSize)
                .subscribe { t: List<NewsItem> -> callback.onResult(t) }
    }

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<NewsItem>) {
        val disp = repository.loadNextPage(params.requestedStartPosition, params.requestedLoadSize)
                .subscribe { t: List<NewsItem> -> callback.onResult(t, 0) }
    }
}