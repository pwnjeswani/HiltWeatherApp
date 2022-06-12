package com.pawanjeswani.app10xweather.di

import com.pawanjeswani.app10xweather.network.APIService
import com.pawanjeswani.app10xweather.network.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideApi(
        remoteDataSource: RemoteDataSource
    ): APIService {
        return remoteDataSource.buildApi(APIService::class.java)
    }
}