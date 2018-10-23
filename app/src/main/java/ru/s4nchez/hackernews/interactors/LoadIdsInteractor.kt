package ru.s4nchez.hackernews.interactors

import io.reactivex.Single
import ru.s4nchez.hackernews.data.repositories.NewsRepository

class LoadIdsInteractor(
        private var repository: NewsRepository
) : Interactor<List<Int>, Any>() {

    override fun buildObservableInteractor(params: Any): Single<List<Int>> =
            repository.getNewStories()
}