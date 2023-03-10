package com.appgain.data.repo

import com.appgain.data.remote.ApiService
import com.appgain.domain.models.MovieDetailsResponse
import com.appgain.domain.models.PopularMovieResponse
import com.appgain.domain.repo.MoviesRepo

class MoviesRepoImp (private val apiService: ApiService) :MoviesRepo{
    override suspend fun getPopularMovies():PopularMovieResponse = apiService.getPopularMovies()
    override suspend fun getMovieDetails(movieId:Int):MovieDetailsResponse = apiService.getMovieDetails(movieId)

}