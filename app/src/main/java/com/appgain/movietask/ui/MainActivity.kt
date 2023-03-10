package com.appgain.movietask.ui

import android.annotation.SuppressLint
import android.opengl.Visibility
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.appgain.movietask.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import android.content.Intent
import android.net.Uri
import androidx.annotation.CallSuper

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navHostFragment =
            supportFragmentManager.findFragmentById(binding.fragmentContainer.id) as NavHostFragment
        navController = navHostFragment.navController
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        binding.toolbar.setupWithNavController(navController,appBarConfiguration)
        navController.addOnDestinationChangedListener { controller, destination, _ ->
            when(destination.id){
                controller.graph.startDestinationId -> binding.toolbar.visibility = VISIBLE
                else -> binding.toolbar.visibility = GONE
            }
        }

    }
    @SuppressLint("MissingSuperCall")
    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        navController.handleDeepLink(intent)
    }
}