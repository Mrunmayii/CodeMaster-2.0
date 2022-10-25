package com.example.codemaster.broadcasts

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi

class SetAlarmBroadcast {

    @RequiresApi(Build.VERSION_CODES.O)
    fun setAlarm(
        context: Context,
        platformName : String,
        contestName : String,
        requestCode : String
    ) {
        val arrayOfAlarmIds = ArrayList<Int>()
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val alarmId = System.currentTimeMillis().toInt()
        val triggerTime = System.currentTimeMillis() + 2000
        val iBroadCast = Intent(context, ReminderReciever::class.java).putExtra("alarmId",alarmId)
        iBroadCast.putExtra("platform",platformName)
        iBroadCast.putExtra("contest",contestName)
        Log.d("kalu", alarmId.toString())

        val pi: PendingIntent = PendingIntent.getBroadcast(
            context,
            alarmId,
            iBroadCast,
            PendingIntent.FLAG_IMMUTABLE
        )
        alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, triggerTime, pi)
    }

}