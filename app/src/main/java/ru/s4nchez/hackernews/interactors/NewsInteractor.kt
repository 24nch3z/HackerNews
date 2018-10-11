package ru.s4nchez.hackernews.interactors

import retrofit2.Call

interface NewsInteractor {
    fun getNewStories(): Call<List<Long>>
}