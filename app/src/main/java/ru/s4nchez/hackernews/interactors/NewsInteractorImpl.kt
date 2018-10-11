package ru.s4nchez.hackernews.interactors

import io.reactivex.Single
import ru.s4nchez.hackernews.data.entities.Item
import ru.s4nchez.hackernews.data.repositories.NewsRepository

class NewsInteractorImpl(var repository: NewsRepository) : NewsInteractor {

    override fun getNewStories(): Single<List<Int>> = repository.getNewStories()

    override fun getItem(id: Int): Single<Item> = repository.getItem(id)
}