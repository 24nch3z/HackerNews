package ru.s4nchez.hackernews.data.repositories

import retrofit2.Call
import ru.s4nchez.hackernews.data.datasource.APIInterface

class NewsRepositoryImpl(var apiInterface: APIInterface) : NewsRepository {

    override fun getNewStories(): Call<List<Long>> = apiInterface.getNewStories()
}