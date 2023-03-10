package com.appgain.domain.usecase

import com.appgain.domain.repo.MoviesRepo

class GetPopularMovies(private val moviesRepo: MoviesRepo) {

    suspend operator fun invoke() = moviesRepo.getPopularMovies()
}