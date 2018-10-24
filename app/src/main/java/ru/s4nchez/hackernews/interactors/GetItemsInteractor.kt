package ru.s4nchez.hackernews.interactors

import io.reactivex.Single
import ru.s4nchez.hackernews.data.entities.Item
import ru.s4nchez.hackernews.data.repositories.NewsRepository

class GetItemsInteractor(
        private var repository: NewsRepository
) : Interactor<List<Item>, GetItemsInteractor.Params>() {

    override fun buildObservableInteractor(params: Params): Single<List<Item>> {
        val requests = ArrayList<Single<Item>>()
        for (id in params.ids) requests.add(repository.getItem(id))
        return Single.zip(requests) { it -> it.map { it as Item } }
    }

    data class Params(val ids: Array<Int>)
}