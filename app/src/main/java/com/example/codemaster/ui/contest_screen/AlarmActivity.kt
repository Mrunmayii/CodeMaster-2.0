package com.example.codemaster.ui.contest_screen

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.codemaster.ui.contest_screen.ui.theme.CodeMasterTheme

class AlarmActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val service = NotificationService()
        val mp : MediaPlayer = MediaPlayer.create(this, Settings.System.DEFAULT_ALARM_ALERT_URI)
        mp.start()
        setContent {
            CodeMasterTheme {
                service.showNotification(this)
                Button(onClick = {
                    mp.stop()
                    cancelAlarm()
                    finish()
                }) {
                    Text(text = "OK")
                }
            }
        }
    }
    fun cancelAlarm() {
        val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
        val myIntent = Intent(applicationContext, ReminderReciever::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            applicationContext, 200, myIntent,
            PendingIntent.FLAG_IMMUTABLE
        )
        alarmManager.cancel(pendingIntent)
    }
}

