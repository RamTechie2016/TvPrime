package com.ramarajan.tvprime.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.ramarajan.tvprime.BuildConfig
import com.ramarajan.tvprime.core.Constants
import com.ramarajan.tvprime.data.ApiService
import com.ramarajan.tvprime.domain.TvPrimeRepository
import com.ramarajan.tvprime.domain.use_cases.UseCases
import com.ramarajan.tvprime.domain.use_cases.details.TvSeriesCredits
import com.ramarajan.tvprime.domain.use_cases.details.TvSeriesDetail
import com.ramarajan.tvprime.domain.use_cases.popular.PopularTvSeriesList
import com.ramarajan.tvprime.domain.use_cases.popular.PopularTvSeriesPagingList
import com.ramarajan.tvprime.domain.use_cases.search_tv_series.SearchTvSeriesPagingList
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    fun providesGson(): Gson = GsonBuilder().setLenient().create()

    @Provides
    @Singleton
    fun clientInterceptor(): Interceptor =
        Interceptor { chain ->
            val request = chain.request()
            val newUrl = request.url.newBuilder()
                .addQueryParameter("api_key", BuildConfig.TMDB_API_KEY)
                .build()

            val newRequest = request.newBuilder()
                .url(newUrl)
                .build()
            chain.proceed(newRequest)
        }


    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson): Retrofit {
        return Retrofit.Builder()
            .client(
                OkHttpClient.Builder().also { client ->
                    val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
                        level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
                        else HttpLoggingInterceptor.Level.NONE
                    }
                    client.addInterceptor(httpLoggingInterceptor)
                    client.connectTimeout(Constants.CONNECT_TIMEOUT, TimeUnit.SECONDS)
                    client.readTimeout(Constants.READ_TIMEOUT, TimeUnit.SECONDS)
                    client.writeTimeout(Constants.WRITE_TIMEOUT, TimeUnit.SECONDS)
                    client.addNetworkInterceptor(clientInterceptor())
                }.build()
            )
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(BuildConfig.BASE_URL)
            .build()
    }


    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)


    @Provides
    @Singleton
    fun useCases(tvPrimeRepository: TvPrimeRepository): UseCases = UseCases(
        PopularTvSeriesPagingList(tvPrimeRepository),
        PopularTvSeriesList(tvPrimeRepository),
        TvSeriesDetail(tvPrimeRepository),
        TvSeriesCredits(tvPrimeRepository),
        SearchTvSeriesPagingList(tvPrimeRepository),
    )
}
