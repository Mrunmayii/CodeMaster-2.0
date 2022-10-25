package com.example.codemaster.utils

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.codemaster.components.TabView
import com.example.codemaster.components.TopAppBar
import com.example.codemaster.ui.cf_problems_screen.CFProblemScreen
import com.example.codemaster.ui.cf_ratingChange_screen.CFRatingChangeScreen
import com.example.codemaster.ui.codechef_screen.Setdetail
import com.example.codemaster.ui.codeforces_screen.MainCFSceen
import com.example.codemaster.ui.home.HomeScreen
import com.example.codemaster.ui.leetcode_screen.MainLCScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavigationGraph(
    navController: NavHostController,
    setAlarm : ()-> Unit
){
    NavHost(
        navController,
        startDestination = Nav.CONTESTS.route
    ){
        composable(Nav.CONTESTS.route){
            TabView(
                topBar = { TopAppBar(
                    title = "CODEMASTER",
                    onNavigate = { navController.navigate(Nav2.HOME.route) }
                ) },
                setAlarm
            )
        }
        composable(Nav.CODECHEF.route){
            Setdetail(
                topBar = {
                    TopAppBar(
                        title = "CODECHEF",
                        onNavigate = { navController.navigate(Nav2.HOME.route) }
                    )
                }
            )
        }
        composable(Nav.CODEFORCES.route){
            MainCFSceen(
                onNavigate= {navController.navigate(it.route)},
                topBar = {
                    TopAppBar(
                        title = "CODEFORCES",
                        onNavigate = { navController.navigate(Nav2.HOME.route) }
                    )
                }
            )
        }
        composable(Nav.LEETCODE.route){
            MainLCScreen(
                topBar = {
                    TopAppBar(
                        title = "LEETCODE",
                        onNavigate = { navController.navigate(Nav2.HOME.route) }
                    )
                }
            )
        }
        composable(Nav2.HOME.route){
            HomeScreen()
        }
        composable(Nav2.CFPROBLEMS.route){
            CFProblemScreen()
        }
        composable(Nav2.CFRATINGS.route){
            CFRatingChangeScreen()
        }
    }
}