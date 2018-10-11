package ru.s4nchez.hackernews.interactors

import retrofit2.Call
import ru.s4nchez.hackernews.data.repositories.NewsRepository

class NewsInteractorImpl(var repository: NewsRepository) : NewsInteractor {

    override fun getNewStories(): Call<List<Long>> = repository.getNewStories()
}