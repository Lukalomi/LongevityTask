package com.example.longevitytask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        checkAuth()
    }


    private fun checkAuth() {
            val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
            val inflater = navHostFragment.navController.navInflater
            val graph = inflater.inflate(R.navigation.nav_graph)

            if (FirebaseAuth.getInstance().currentUser !== null){
                graph.setStartDestination(R.id.main)
            }else {
                graph.setStartDestination(R.id.authorization)
            }

            val navController = navHostFragment.navController
            navController.setGraph(graph, intent.extras)

    }
}