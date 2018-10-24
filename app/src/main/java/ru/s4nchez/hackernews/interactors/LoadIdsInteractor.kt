package ru.s4nchez.hackernews.interactors

import io.reactivex.Single
import ru.s4nchez.hackernews.data.repositories.NewsRepository

class LoadIdsInteractor(
        private var repository: NewsRepository
) : Interactor<Boolean, EmptyParams>() {

    override fun buildObservableInteractor(params: EmptyParams): Single<Boolean> =
            repository.getNewStories()
}