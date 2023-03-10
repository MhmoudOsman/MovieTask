package com.appgain.movietask.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.HandlerCompat.postDelayed
import androidx.lifecycle.lifecycleScope
import com.appgain.movietask.databinding.ActivitySplashScreenBinding
import com.appgain.movietask.viewmodels.PopularMoviesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)

    }
}