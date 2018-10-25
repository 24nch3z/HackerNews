package ru.s4nchez.hackernews.data

import android.arch.paging.DataSource
import ru.s4nchez.hackernews.data.entities.NewsItem
import ru.s4nchez.hackernews.data.repositories.NewsRepository

class PagedNewsItemsDataSourceFactory(
        private val repository: NewsRepository
) : DataSource.Factory<Int, NewsItem>() {

    override fun create(): DataSource<Int, NewsItem> =
            PagedNewsItemsDataSource(repository)
}