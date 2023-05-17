package com.pavelt.ghrelin

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.pavelt.ghrelin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.bottomNavView
        val navController = findNavController(R.id.nav_host_fragment)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            navView.isVisible =
                destination.id != R.id.fragmentAuthorization
        }

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.fragmentCatalog, R.id.fragmentPersonalArea
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        val callback = object : OnBackPressedCallback(
            true
        ) {
            override fun handleOnBackPressed() {
                navController.addOnDestinationChangedListener { _, destination, _ ->
                    if (destination.id == R.id.fragmentAuthorization){
                        finish()
                    }
                }
//                navController.navigate(R.id.fragmentCatalog)
            }
        }
        onBackPressedDispatcher.addCallback(callback)
    }
}