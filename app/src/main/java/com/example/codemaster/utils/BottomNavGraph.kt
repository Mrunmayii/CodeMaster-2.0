package com.example.codemaster.utils

import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.codemaster.components.TabView
import com.example.codemaster.ui.codechef_screen.Setdetail
import com.example.codemaster.ui.codeforces_screen.MainCFSceen
import com.example.codemaster.ui.leetcode_screen.MainLCScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavigationGraph(
    navController: NavHostController,
    intent : Intent
){
    NavHost(
        navController,
        startDestination = Nav.CONTESTS.route
    ){
        composable(Nav.CONTESTS.route){
            TabView(intent)
        }
        composable(Nav.CODECHEF.route){
            Setdetail()
        }
        composable(Nav.CODEFORCES.route){
            MainCFSceen()
        }
        composable(Nav.LEETCODE.route){
            MainLCScreen()
        }

    }

}