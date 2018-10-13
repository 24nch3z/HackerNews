package ru.s4nchez.hackernews.di

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.s4nchez.hackernews.data.datasource.APIInterface
import ru.s4nchez.hackernews.data.repositories.NewsRepository
import ru.s4nchez.hackernews.data.repositories.NewsRepositoryImpl
import ru.s4nchez.hackernews.interactors.NewsInteractor
import ru.s4nchez.hackernews.interactors.NewsInteractorImpl
import ru.s4nchez.hackernews.ui.list.ListPresenter
import javax.inject.Singleton

@Module
class AppModule(private val context: Context) {

    @Provides
    @Singleton
    fun provideContext() = context

    @Provides
    @Singleton
    fun provideNewsRepository(apiInterface: APIInterface):
            NewsRepository = NewsRepositoryImpl(apiInterface)

    @Provides
    fun provideListPresenter(interactor: NewsInteractor):
            ListPresenter = ListPresenter(interactor)

    @Provides
    fun provideNewsInteractor(repository: NewsRepository):
            NewsInteractor = NewsInteractorImpl(repository)
}