package ru.s4nchez.hackernews.di.module

import dagger.Module
import dagger.Provides
import ru.s4nchez.hackernews.data.AppDatabase
import ru.s4nchez.hackernews.data.PagedNewsItemsDataSourceFactory
import ru.s4nchez.hackernews.data.datasource.APIInterface
import ru.s4nchez.hackernews.data.repositories.NewsRepository
import ru.s4nchez.hackernews.data.repositories.NewsRepositoryImpl
import ru.s4nchez.hackernews.di.NewsScope
import ru.s4nchez.hackernews.interactors.LoadIdsInteractor
import ru.s4nchez.hackernews.interactors.PagingLoadNextPageInteractor
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
                             pagingLoadNextPageInteractor: PagingLoadNextPageInteractor):
            NewsPresenter = NewsPresenter(loadIdsInteractor, pagingLoadNextPageInteractor)

    @Provides
    @NewsScope
    fun provideLoadIdsInteractor(repository: NewsRepository):
            LoadIdsInteractor = LoadIdsInteractor(repository)

    @Provides
    @NewsScope
    fun providePagedNewsItemsDataSourceFactory(repository: NewsRepository):
            PagedNewsItemsDataSourceFactory = PagedNewsItemsDataSourceFactory(repository)

    @Provides
    @NewsScope
    fun providePagingLoadNextPageInteractor(factory: PagedNewsItemsDataSourceFactory):
            PagingLoadNextPageInteractor = PagingLoadNextPageInteractor(factory)

}