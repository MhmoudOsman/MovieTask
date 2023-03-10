package com.appgain.movietask.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appgain.domain.models.MovieDetailsResponse
import com.appgain.domain.models.PopularMovieResponse
import com.appgain.domain.usecase.GetMovieDetails
import com.appgain.domain.usecase.GetPopularMovies
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class PopularMoviesViewModel @Inject constructor (private val get_PopularMovies: GetPopularMovies) : ViewModel() {

    private val _popularMovie : MutableStateFlow<PopularMovieResponse?> = MutableStateFlow(null)
    val popularMovie : StateFlow<PopularMovieResponse?> = _popularMovie

    fun getPopularMovies(){
        viewModelScope.launch {
            try {
                _popularMovie.value = get_PopularMovies()
                Log.e("MealsViewModel", _popularMovie.toString() )

            }catch (e: Exception){
                Log.e("MealsViewModel", e.message.toString() )
            }
        }
    }
}