package ru.s4nchez.hackernews.di

import dagger.Module
import dagger.Provides
import ru.s4nchez.hackernews.interactors.NewsInteractor
import ru.s4nchez.hackernews.interactors.NewsInteractorImpl
import ru.s4nchez.hackernews.ui.list.ListPresenter

@Module
class AppModule {

    @Provides
    fun provideListPresenter(interactor: NewsInteractor):
            ListPresenter = ListPresenter(interactor)

    @Provides
    fun provideNewsInteractor():
            NewsInteractor = NewsInteractorImpl()
}