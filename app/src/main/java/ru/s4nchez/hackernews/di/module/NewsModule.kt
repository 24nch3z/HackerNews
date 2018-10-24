package ru.s4nchez.hackernews.di.module

import dagger.Module
import dagger.Provides
import ru.s4nchez.hackernews.data.datasource.APIInterface
import ru.s4nchez.hackernews.data.repositories.NewsRepository
import ru.s4nchez.hackernews.data.repositories.NewsRepositoryImpl
import ru.s4nchez.hackernews.di.NewsScope
import ru.s4nchez.hackernews.interactors.GetItemsInteractor
import ru.s4nchez.hackernews.interactors.LoadIdsInteractor
import ru.s4nchez.hackernews.ui.news.NewsPresenter

@Module
class NewsModule {

    @Provides
    @NewsScope
    fun provideNewsRepository(apiInterface: APIInterface):
            NewsRepository = NewsRepositoryImpl(apiInterface)

    @Provides
    @NewsScope
    fun provideListPresenter(loadIdsInteractor: LoadIdsInteractor, getItemsInteractor: GetItemsInteractor):
            NewsPresenter = NewsPresenter(loadIdsInteractor, getItemsInteractor)

    @Provides
    @NewsScope
    fun provideLoadIdsInteractor(repository: NewsRepository):
            LoadIdsInteractor = LoadIdsInteractor(repository)

    @Provides
    @NewsScope
    fun provideGetItemsInteractor(repository: NewsRepository):
            GetItemsInteractor = GetItemsInteractor(repository)

}