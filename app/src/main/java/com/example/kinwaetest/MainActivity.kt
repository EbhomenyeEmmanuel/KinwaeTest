package com.example.kinwaetest

import android.os.Bundle
import android.os.StrictMode
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.kinwaetest.databinding.ActivityMainBinding
import com.example.kinwaetest.ui.bottomNavigation.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var bind: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)
        enableStrictMode()
        setUpBottomNav()

    }

    private fun setUpBottomNav() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.main_nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        bind.bottomNav.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.homeFragment -> {
                }
                R.id.walletFragment -> {

                }
                R.id.profileFragment -> {
                }
                else -> {

                }
            }
        }
    }


    private fun enableStrictMode() {
        if (BuildConfig.DEBUG) {
            val policy: StrictMode.ThreadPolicy =
                StrictMode.ThreadPolicy.Builder().detectAll().penaltyLog().build()
            StrictMode.setThreadPolicy(policy)
            val vmPolicy: StrictMode.VmPolicy =
                StrictMode.VmPolicy.Builder().detectAll().penaltyLog().build()
            StrictMode.setVmPolicy(vmPolicy)
        }
    }
}
