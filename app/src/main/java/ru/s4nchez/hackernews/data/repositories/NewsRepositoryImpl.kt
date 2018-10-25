package ru.s4nchez.hackernews.data.repositories

import android.annotation.SuppressLint
import io.reactivex.Maybe
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.s4nchez.hackernews.data.AppDatabase
import ru.s4nchez.hackernews.data.datasource.APIInterface
import ru.s4nchez.hackernews.data.entities.NewsItem
import timber.log.Timber

class NewsRepositoryImpl(
        private var apiInterface: APIInterface,
        private var db: AppDatabase
) : NewsRepository {

    private val PAGE_SIZE = 10
    private val ids = ArrayList<Int>()
    private val items = ArrayList<NewsItem>()

    override fun getNewStories(): Single<Boolean> {
        if (!ids.isEmpty()) return Single.just(false)

        return apiInterface.getNewStories()
                .flatMap {
                    ids.addAll(it)
                    Single.just(true)
                }
    }

    override fun loadNextPage(): Single<List<NewsItem>> {
        val pageSize = Math.min(ids.size - items.size, PAGE_SIZE)
        var position = items.size
        val newIds = Array(pageSize) { _ -> ids[position++] }
        val requests = newIds.map { getItem(it) }

        return Single
                .zip(requests) { it -> it.map { it as NewsItem } }
                .flatMap {
                    items.addAll(it)
                    Single.just(items)
                }
    }

    // Умножение на 1000 нужно для перевода из секунд в миллисекунды
    private fun getItem(id: Int): Single<NewsItem> {
        return apiInterface.getItem(id)
                .flatMap {
                    if (it.time != null) it.time = it.time!! * 1000
                    db.newsItemDao().insert(it)
                    Single.just(it)
                }
    }
}