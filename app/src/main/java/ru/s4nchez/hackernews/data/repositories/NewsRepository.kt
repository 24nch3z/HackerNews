package ru.s4nchez.hackernews.data.repositories

import retrofit2.Call

interface NewsRepository {
    fun getNewStories(): Call<List<Long>>
}