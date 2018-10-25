package ru.s4nchez.hackernews.interactors

import io.reactivex.Single
import ru.s4nchez.hackernews.data.repositories.NewsRepository

class LoadIdsInteractor(
        private val repository: NewsRepository
) : Interactor<Boolean>() {

    override fun buildObservableInteractor(): Single<Boolean> =
            repository.getNewStories()
}