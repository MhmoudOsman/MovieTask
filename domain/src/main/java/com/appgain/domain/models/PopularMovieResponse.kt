package com.appgain.domain.models

data class PopularMovieResponse(
    val page: Int,
    val results: List<Movie>,
    val total_pages: Int,
    val total_results: Int
)