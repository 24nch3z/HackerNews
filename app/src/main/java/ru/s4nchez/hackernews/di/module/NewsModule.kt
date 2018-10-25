package ru.s4nchez.hackernews.di.module

import dagger.Module
import dagger.Provides
import ru.s4nchez.hackernews.data.AppDatabase
import ru.s4nchez.hackernews.data.datasource.APIInterface
import ru.s4nchez.hackernews.data.repositories.NewsRepository
import ru.s4nchez.hackernews.data.repositories.NewsRepositoryImpl
import ru.s4nchez.hackernews.di.NewsScope
import ru.s4nchez.hackernews.interactors.LoadIdsInteractor
import ru.s4nchez.hackernews.interactors.LoadNextPageInteractor
import ru.s4nchez.hackernews.ui.news.NewsPresenter

@Module
class NewsModule {

    @Provides
    @NewsScope
    fun provideNewsRepository(apiInterface: APIInterface, db: AppDatabase):
            NewsRepository = NewsRepositoryImpl(apiInterface, db)

    @Provides
    @NewsScope
    fun provideListPresenter(loadIdsInteractor: LoadIdsInteractor,
                             loadNextPageInteractor: LoadNextPageInteractor):
            NewsPresenter = NewsPresenter(loadIdsInteractor, loadNextPageInteractor)

    @Provides
    @NewsScope
    fun provideLoadIdsInteractor(repository: NewsRepository):
            LoadIdsInteractor = LoadIdsInteractor(repository)

    @Provides
    @NewsScope
    fun provideLoadNextPageInteractor(repository: NewsRepository):
            LoadNextPageInteractor = LoadNextPageInteractor(repository)

}