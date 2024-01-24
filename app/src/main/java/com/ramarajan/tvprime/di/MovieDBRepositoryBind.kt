package com.ramarajan.tvprime.di

import com.ramarajan.tvprime.data.repository.TvPrimeRepositoryImpl
import com.ramarajan.tvprime.domain.TvPrimeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class TvPrimeRepositoryBind {

    @Binds
    @Singleton
    abstract fun bindRepository(tvPrimeRepositoryImpl: TvPrimeRepositoryImpl): TvPrimeRepository

}