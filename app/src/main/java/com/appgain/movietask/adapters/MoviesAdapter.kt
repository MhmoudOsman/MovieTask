package com.appgain.movietask.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.appgain.domain.models.Movie
import com.appgain.movietask.databinding.ItemMoviesBinding
import com.bumptech.glide.Glide

class MoviesAdapter : ListAdapter<Movie, MoviesAdapter.MovieHolder>(MoviesDiffCallback()) {
    var onItemClick: ((Movie) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val binding = ItemMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        holder.onBind(getItem(position))

    }


    inner class MovieHolder(private val itemBinding: ItemMoviesBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun onBind(movie: Movie) {
            itemBinding.movie = movie
            Glide.with(itemBinding.root.context)
                .load("https://image.tmdb.org/t/p/original${movie.poster_path}")
                .into(itemBinding.imgMovie)
            itemView.setOnClickListener { onItemClick?.invoke(movie) }
        }
    }

    class MoviesDiffCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }


}