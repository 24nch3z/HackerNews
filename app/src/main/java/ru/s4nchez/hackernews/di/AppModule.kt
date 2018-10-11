package ru.s4nchez.hackernews.di

import android.content.Context
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.s4nchez.hackernews.data.datasource.APIInterface
import ru.s4nchez.hackernews.data.repositories.NewsRepository
import ru.s4nchez.hackernews.data.repositories.NewsRepositoryImpl
import ru.s4nchez.hackernews.interactors.NewsInteractor
import ru.s4nchez.hackernews.interactors.NewsInteractorImpl
import ru.s4nchez.hackernews.ui.list.ListPresenter
import javax.inject.Singleton

@Module
class AppModule(private val context: Context) {

    val BASE_URL = "https://hacker-news.firebaseio.com/v0/"

    @Provides
    @Singleton
    fun provideContext() = context

    @Provides
    @Singleton
    fun provideRetrofitClient(context: Context): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    @Provides
    @Singleton
    fun provideAPIInterface(retrofit: Retrofit):
            APIInterface = retrofit.create(APIInterface::class.java)

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