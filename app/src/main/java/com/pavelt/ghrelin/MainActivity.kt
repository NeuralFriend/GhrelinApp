package com.pavelt.ghrelin

import android.os.Bundle
import android.widget.Toast
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

        val backStack = navController.backQueue

        val previousFragmentTag = backStack[backStack.size - 1].destination.label
        if (previousFragmentTag == "fragmentAuthorization") {
            finish()
        }

        navController.addOnDestinationChangedListener { _, destination, _ ->
            navView.isVisible =
                !(destination.id == R.id.fragmentActions || destination.id == R.id.fragmentAuthorization || destination.id == R.id.fragmentNonauthorizedMenu)
        }

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.fragmentCatalog, R.id.fragmentPersonalArea
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)



    }

    override fun onBackPressed() {
        val navController = findNavController(R.id.nav_host_fragment)
        val backStack = navController.backQueue

        val previousFragmentTag = backStack[backStack.size - 2].destination.label

        if (previousFragmentTag == "fragment_authorization" || previousFragmentTag == "fragment_actions") {
            moveTaskToBack(true)
        }
        else{
            super.onBackPressed()
        }
    }
}