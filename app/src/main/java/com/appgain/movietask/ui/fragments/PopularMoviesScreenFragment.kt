package com.appgain.movietask.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.appgain.movietask.adapters.MoviesAdapter
import com.appgain.movietask.databinding.FragmentPopularMoviesScreenBinding
import com.appgain.movietask.viewmodels.PopularMoviesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass.
 * Use the [PopularMoviesScreenFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
private const val TAG = "PopularMoviesScreen"
@AndroidEntryPoint
class PopularMoviesScreenFragment : Fragment() {

    private val viewModel: PopularMoviesViewModel by viewModels()
    private lateinit var binding :FragmentPopularMoviesScreenBinding
    private val adapter = MoviesAdapter()
    private lateinit var navController :NavController


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPopularMoviesScreenBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        viewModel.getPopularMovies()
                viewModel.getPopularMovies()
                lifecycleScope.launch{
                    viewModel.popularMovie.collect{
                        Log.d(TAG, "response: ${it.toString()}")
                        adapter.submitList(it?.results)
                        binding.recPopularMovies.adapter = adapter
                        adapter.onItemClick = { movie ->
                            val destination = PopularMoviesScreenFragmentDirections.actionPopularScreenToDetailsScreen(movie.id)
                            navController.navigate(destination)

                        }
                    }
                }
    }
}