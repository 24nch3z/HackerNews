package ru.s4nchez.hackernews.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.s4nchez.hackernews.data.datasource.APIInterface
import ru.s4nchez.hackernews.data.datasource.BASE_URL
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetModule {

    private val networkCacheInterceptor = Interceptor { chain ->
        val response = chain.proceed(chain.request())

        val cacheControl = CacheControl.Builder()
                .maxAge(5, TimeUnit.MINUTES)
                .build()

        response.newBuilder()
                .header("Cache-Control", cacheControl.toString())
                .build()
    }


    private fun getCache(context: Context): Cache {
        val cacheSize = 10 * 1024 * 1024
        val httpCacheDirectory = File(context.cacheDir, "http-cache")
        return Cache(httpCacheDirectory, cacheSize.toLong())
    }

    @Provides
    @Singleton
    fun provideRetrofitClient(context: Context): Retrofit {
        val cache = getCache(context)

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
                .cache(cache)
                .addNetworkInterceptor(networkCacheInterceptor)
                .addInterceptor(loggingInterceptor)
                .build()

        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(httpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    @Provides
    @Singleton
    fun provideAPIInterface(retrofit: Retrofit):
            APIInterface = retrofit.create(APIInterface::class.java)
}