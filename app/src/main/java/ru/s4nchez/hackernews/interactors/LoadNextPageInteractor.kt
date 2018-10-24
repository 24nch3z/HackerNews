package ru.s4nchez.hackernews.interactors

import io.reactivex.Single
import ru.s4nchez.hackernews.data.entities.Item
import ru.s4nchez.hackernews.data.repositories.NewsRepository

class LoadNextPageInteractor(
        private var repository: NewsRepository
) : Interactor<List<Item>, LoadNextPageInteractor.Params>() {

    override fun buildObservableInteractor(params: Params): Single<List<Item>> = repository.loadNextPage()

    class Params
}