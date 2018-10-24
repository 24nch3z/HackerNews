package ru.s4nchez.hackernews.interactors

import io.reactivex.Single
import ru.s4nchez.hackernews.data.entities.Item
import ru.s4nchez.hackernews.data.repositories.NewsRepository

class LoadNextPageInteractor(
        private var repository: NewsRepository
) : Interactor<List<Item>, EmptyParams>() {

    override fun buildObservableInteractor(params: EmptyParams): Single<List<Item>> =
            repository.loadNextPage()
}