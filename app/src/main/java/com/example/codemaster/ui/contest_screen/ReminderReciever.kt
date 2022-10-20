package com.example.codemaster.ui.contest_screen

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi

class ReminderReciever : BroadcastReceiver() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onReceive(context: Context, intent: Intent) {

        val alarmIntent = Intent(context, AlarmActivity::class.java)
        alarmIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        alarmIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        context.startActivity(alarmIntent)

    }

}