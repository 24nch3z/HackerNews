package ru.s4nchez.hackernews.interactors

import io.reactivex.Single
import ru.s4nchez.hackernews.data.entities.NewsItem
import ru.s4nchez.hackernews.data.repositories.NewsRepository

class LoadNextPageInteractor(
        private val repository: NewsRepository
) : Interactor<List<NewsItem>>() {

    override fun buildObservableInteractor(): Single<List<NewsItem>> =
            repository.loadNextPage()
}