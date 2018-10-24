package ru.s4nchez.hackernews.interactors

import io.reactivex.Single
import ru.s4nchez.hackernews.data.entities.NewsItem
import ru.s4nchez.hackernews.data.repositories.NewsRepository

class LoadNextPageInteractor(
        private var repository: NewsRepository
) : Interactor<List<NewsItem>, EmptyParams>() {

    override fun buildObservableInteractor(params: EmptyParams): Single<List<NewsItem>> =
            repository.loadNextPage()
}