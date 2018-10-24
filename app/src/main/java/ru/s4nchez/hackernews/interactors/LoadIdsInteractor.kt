package ru.s4nchez.hackernews.interactors

import io.reactivex.Single
import ru.s4nchez.hackernews.data.repositories.NewsRepository

class LoadIdsInteractor(
        private var repository: NewsRepository
) : Interactor<Boolean, LoadIdsInteractor.Params>() {

    override fun buildObservableInteractor(params: Params): Single<Boolean> =
            repository.getNewStories()

    class Params
}