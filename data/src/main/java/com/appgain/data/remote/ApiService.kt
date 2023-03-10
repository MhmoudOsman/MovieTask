package com.appgain.data.remote

import com.appgain.domain.models.MovieDetailsResponse
import com.appgain.domain.models.PopularMovieResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("popular")
    suspend fun getPopularMovies():PopularMovieResponse
    @GET("{movieId}")
    suspend fun getMovieDetails(@Path("movieId") movieId:Int):MovieDetailsResponse
}