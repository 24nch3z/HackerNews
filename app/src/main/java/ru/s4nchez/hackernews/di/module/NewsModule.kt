package ru.s4nchez.hackernews.di.module

import dagger.Module
import dagger.Provides
import ru.s4nchez.hackernews.data.datasource.APIInterface
import ru.s4nchez.hackernews.data.repositories.NewsRepository
import ru.s4nchez.hackernews.data.repositories.NewsRepositoryImpl
import ru.s4nchez.hackernews.di.NewsScope
import ru.s4nchez.hackernews.interactors.LoadIdsInteractor
import ru.s4nchez.hackernews.interactors.NewsInteractor
import ru.s4nchez.hackernews.interactors.NewsInteractorImpl
import ru.s4nchez.hackernews.ui.list.ListPresenter
import javax.inject.Singleton

@Module
class NewsModule {

    @Provides
    @NewsScope
    fun provideNewsRepository(apiInterface: APIInterface):
            NewsRepository = NewsRepositoryImpl(apiInterface)

    @Provides
    @NewsScope
    fun provideListPresenter(interactor: NewsInteractor, loadIdsInteractor: LoadIdsInteractor):
            ListPresenter = ListPresenter(interactor, loadIdsInteractor)

    @Provides
    @NewsScope
    fun provideNewsInteractor(repository: NewsRepository):
            NewsInteractor = NewsInteractorImpl(repository)

    @Provides
    @NewsScope
    fun provideLoadIdsInteractor(repository: NewsRepository):
            LoadIdsInteractor = LoadIdsInteractor(repository)

}