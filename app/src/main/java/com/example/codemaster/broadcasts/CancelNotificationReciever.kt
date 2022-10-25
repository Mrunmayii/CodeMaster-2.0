package com.example.codemaster.broadcasts

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi


class CancelNotificationReciever : BroadcastReceiver(){
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onReceive(context: Context, intent: Intent) {

//        val alarm = System.currentTimeMillis().toInt()
        val alarmId = intent.getIntExtra("alarm",0)
        Log.d("kalu", "alarm Id is :  $alarmId")
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        ReminderReciever.ringtoneSound.stop()
        notificationManager.cancel(alarmId)

    }
}

