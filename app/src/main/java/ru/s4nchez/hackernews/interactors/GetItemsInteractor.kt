package ru.s4nchez.hackernews.interactors

import io.reactivex.Single
import ru.s4nchez.hackernews.data.entities.Item
import ru.s4nchez.hackernews.data.repositories.NewsRepository

class GetItemsInteractor(
        private var repository: NewsRepository
) : Interactor<List<Item>, Array<Int>>() {

    override fun buildObservableInteractor(params: Array<Int>): Single<List<Item>> {
        val requests = ArrayList<Single<Item>>()
        for (id in params) requests.add(repository.getItem(id))
        return Single.zip(requests) { it -> it.map { it as Item } }
    }
}