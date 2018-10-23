package ru.s4nchez.hackernews.interactors

import io.reactivex.Single
import ru.s4nchez.hackernews.data.entities.Item
import ru.s4nchez.hackernews.data.repositories.NewsRepository

class NewsInteractorImpl(var repository: NewsRepository) : NewsInteractor {

    override fun getNewStories(): Single<List<Int>> = repository.getNewStories()

    override fun getItems(ids: Array<Int>): Single<List<Item>> {
        val requests = ArrayList<Single<Item>>()
        for (id in ids) requests.add(repository.getItem(id))
        return Single.zip(requests) { it -> it.map { it as Item } }
    }
}