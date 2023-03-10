package com.appgain.domain.usecase

import com.appgain.domain.repo.MoviesRepo

class GetMovieDetails(private val moviesRepo: MoviesRepo) {
    suspend operator fun invoke(movieId:Int) = moviesRepo.getMovieDetails(movieId)
}