package com.appgain.movietask.ui.fragments

import android.content.Context
import android.graphics.Bitmap
import android.os.Bundle
import android.renderscript.Allocation
import android.renderscript.Element
import android.renderscript.RenderScript
import android.renderscript.ScriptIntrinsicBlur
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.util.rangeTo
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.appgain.movietask.databinding.FragmentMovieDetailsScreenBinding
import com.appgain.movietask.viewmodels.MovieDetailsViewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.security.MessageDigest
import kotlin.math.roundToInt


/**
 * A simple [Fragment] subclass.
 * Use the [MovieDetailsScreenFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
private const val TAG = "MovieDetailsScreen"
@AndroidEntryPoint
class MovieDetailsScreenFragment : Fragment() {
    private val viewModel: MovieDetailsViewModel by viewModels()
    private val args: MovieDetailsScreenFragmentArgs by navArgs()
    private lateinit var binding: FragmentMovieDetailsScreenBinding
    private lateinit var navController: NavController
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieDetailsScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        val id = args.movieId
        viewModel.getMovieDetails(id)
        lifecycleScope.launch {
            viewModel.movieDetails.collect {
                Log.d(TAG, "response: ${it.toString()}")

                Glide.with(this@MovieDetailsScreenFragment)
                    .load("https://image.tmdb.org/t/p/original${it?.backdrop_path}")
                    .transform(BlurTransformation(context))
                    .into(binding.imgBackdrop)

                Glide.with(this@MovieDetailsScreenFragment)
                    .load("https://image.tmdb.org/t/p/original${it?.poster_path}")
                    .into(binding.imgPoster)

                binding.movieName.text = it?.original_title
                var rate = "Rate : ${it?.vote_average?.div(10)?.times(100)?.roundToInt()} %"
                binding.movieRate.text = rate
                binding.movieOverview.text = it?.overview

            }
        }


    }


    class BlurTransformation(context: Context?) : BitmapTransformation() {
        private val rs: RenderScript

        init {
            rs = RenderScript.create(context)
        }

        override fun transform(
            pool: BitmapPool, toTransform: Bitmap, outWidth: Int, outHeight: Int
        ): Bitmap {
            val blurredBitmap = toTransform.copy(Bitmap.Config.ARGB_8888, true)
            val input = Allocation.createFromBitmap(
                rs, blurredBitmap, Allocation.MipmapControl.MIPMAP_FULL, Allocation.USAGE_SHARED
            )
            val output = Allocation.createTyped(rs, input.type)
            val script = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs))
            script.setInput(input)

            // Set the blur radius
            script.setRadius(10f)
            script.forEach(output)

            // Copy the output to the blurred bitmap
            output.copyTo(blurredBitmap)
            return blurredBitmap
        }

        override fun updateDiskCacheKey(messageDigest: MessageDigest) {
            messageDigest.update("blur transformation".toByteArray())
        }
    }
}