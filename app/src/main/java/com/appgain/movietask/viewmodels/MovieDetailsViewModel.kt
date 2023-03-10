package com.appgain.movietask.viewmodels

import android.app.usage.ConfigurationStats
import android.content.pm.ConfigurationInfo
import android.content.res.Configuration
import android.util.Log
import androidx.core.os.ConfigurationCompat
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
class MovieDetailsViewModel @Inject constructor (private val get_MovieDetails: GetMovieDetails) : ViewModel() {

    private val _movieDetails : MutableStateFlow<MovieDetailsResponse?> = MutableStateFlow(null)
    val movieDetails : StateFlow<MovieDetailsResponse?> = _movieDetails

    fun getMovieDetails(movieId:Int){
        viewModelScope.launch {
            try {
                _movieDetails.value = get_MovieDetails(movieId)
            }catch (e: Exception){
                Log.e("MealsViewModel", e.message.toString() )
            }
        }
    }
}