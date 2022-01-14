package com.example.kinwaetest.ui.auth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.example.kinwaetest.MainActivity
import com.example.kinwaetest.R
import com.example.kinwaetest.data.preferences.KinwaeAuthPreferences
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AuthActivity : AppCompatActivity() {
    private val navController by lazy {
        (supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment).navController
    }

    @Inject
    lateinit var authPreferences: KinwaeAuthPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        if (authPreferences.getUserLoginStatus()) {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

}