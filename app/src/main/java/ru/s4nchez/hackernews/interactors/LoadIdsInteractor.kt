package ru.s4nchez.hackernews.interactors

import io.reactivex.Single
import ru.s4nchez.hackernews.data.repositories.NewsRepository

class LoadIdsInteractor(
        private var repository: NewsRepository
) : Interactor<List<Int>, LoadIdsInteractor.Params>() {

    override fun buildObservableInteractor(params: Params): Single<List<Int>> =
            repository.getNewStories()

    class Params
}