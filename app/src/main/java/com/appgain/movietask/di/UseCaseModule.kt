package com.appgain.movietask.di

import android.util.Log
import com.appgain.domain.repo.MoviesRepo
import com.appgain.domain.usecase.GetMovieDetails
import com.appgain.domain.usecase.GetPopularMovies
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

private const val TAG = "UseCaseModule"
@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    fun provideGetPopularMoviesUseCase(moviesRepo: MoviesRepo): GetPopularMovies {
        return GetPopularMovies(moviesRepo)
    }

    @Provides
    fun provideGetMovieDetailsUseCase(moviesRepo: MoviesRepo): GetMovieDetails {
        return GetMovieDetails(moviesRepo)
    }

}