package com.appgain.domain.repo

import com.appgain.domain.models.MovieDetailsResponse
import com.appgain.domain.models.PopularMovieResponse

interface MoviesRepo {
    suspend fun getPopularMovies():PopularMovieResponse
    suspend fun getMovieDetails(movieId:Int):MovieDetailsResponse

}