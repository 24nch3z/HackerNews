package ru.s4nchez.hackernews.data.repositories

import io.reactivex.Single
import ru.s4nchez.hackernews.data.datasource.APIInterface
import ru.s4nchez.hackernews.data.entities.Item

class NewsRepositoryImpl(var apiInterface: APIInterface) : NewsRepository {

    override fun getNewStories(): Single<List<Int>> = apiInterface.getNewStories()

    // Умножение на 1000 нужно для перевода из секунд в миллисекунды
    override fun getItem(id: Int): Single<Item> =
            apiInterface.getItem(id)
                    .flatMap {
                        if (it.time != null) it.time = it.time!! * 1000
                        Single.just(it)
                    }
}