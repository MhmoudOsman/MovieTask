package com.appgain.movietask.di

import com.appgain.data.remote.ApiService
import com.appgain.data.repo.MoviesRepoImp
import com.appgain.domain.repo.MoviesRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {

    @Provides
    fun provideRepo(apiService: ApiService): MoviesRepo {
        return MoviesRepoImp(apiService)
    }
}