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
import androidx.navigation.compose.rememberNavController
import com.example.codemaster.components.BottomNav
import com.example.codemaster.components.TopAppBar
import com.example.codemaster.ui.contest_screen.ReminderReciever
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
            Scaffold(
                topBar = { TopAppBar() },
                bottomBar = { BottomNav(navController = navController) }
            ) {
                NavigationGraph(
                    navController = navController,
                    setAlarm = { setAlarm() }
                )
            }
////            HomeScreen()
////            CFRatingChangeScreen()
////            CFProblemScreen()
//
//        }
        }
    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun setAlarm() {
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val triggerTime = System.currentTimeMillis() + 2000
        val iBroadCast = Intent(this, ReminderReciever::class.java)
        val pi: PendingIntent = PendingIntent.getBroadcast(
            this,
            100,
            iBroadCast,
            PendingIntent.FLAG_IMMUTABLE
        )
        alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, triggerTime, pi)
    }
}
