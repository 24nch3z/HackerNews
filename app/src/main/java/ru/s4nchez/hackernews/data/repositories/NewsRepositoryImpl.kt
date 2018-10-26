package ru.s4nchez.hackernews.data.repositories

import io.reactivex.Completable
import io.reactivex.Single
import ru.s4nchez.hackernews.data.AppDatabase
import ru.s4nchez.hackernews.data.datasource.APIInterface
import ru.s4nchez.hackernews.data.entities.NewsItem

class NewsRepositoryImpl(
        private val apiInterface: APIInterface,
        private val db: AppDatabase
) : NewsRepository {

    private val ids = ArrayList<Int>()

    override fun getNewStories(): Single<Boolean> {
        if (!ids.isEmpty()) return Single.just(false)
        return apiInterface.getNewStories()
                .flatMap {
                    ids.addAll(it)
                    Single.just(true)
                }
    }

    override fun loadNextPage(offset: Int, count: Int): Single<List<NewsItem>> {
        val limit = Math.min(offset + count - 1, ids.size - 1)
        val newIds = ArrayList<Int>()
        for (i in offset..limit) newIds.add(ids[i])
        val requests = newIds.map { getItem(it) }
        return Single.zip(requests) { it -> it.map { it as NewsItem } }
                .onErrorResumeNext { Single.just(ArrayList()) }
    }

    // Умножение на 1000 нужно для перевода из секунд в миллисекунды
    private fun getItem(id: Int): Single<NewsItem> {
        val getFromNetwork = apiInterface.getItem(id)
                .map { it.time = it.time!! * 1000; it }
                .flatMapCompletable { Completable.fromAction { db.newsItemDao().insert(it) } }
                .andThen(db.newsItemDao().getById(id))

        return db.newsItemDao().getById(id)
                .onErrorResumeNext(getFromNetwork)
    }
}