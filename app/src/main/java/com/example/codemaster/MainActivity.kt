package com.example.codemaster

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.codemaster.components.BottomNav
import com.example.codemaster.ui.cf_problems_screen.CFProblemScreen
import com.example.codemaster.utils.NavigationGraph
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            var showBottomBar by rememberSaveable { mutableStateOf(true) }
            val navBackStackEntry by navController.currentBackStackEntryAsState()

            showBottomBar = when(navBackStackEntry?.destination?.route){
                "home" -> false
                "cf_problems" ->false
                "cf_ratings" ->false
                else -> true
            }
            Scaffold(
//                topBar = { TopAppBar() },
                bottomBar = { if(showBottomBar) BottomNav(navController = navController) }
            ) {
                NavigationGraph(
                    navController = navController,
                    intent = intent
                )
            }
////            HomeScreen()
////            CFRatingChangeScreen()
//            CFProblemScreen()
//        }
        }
    }
}


