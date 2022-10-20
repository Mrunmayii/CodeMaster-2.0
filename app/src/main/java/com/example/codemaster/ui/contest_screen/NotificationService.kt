package com.example.codemaster.ui.contest_screen

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.example.codemaster.R

class NotificationService {
    @RequiresApi(Build.VERSION_CODES.O)
    fun showNotification(
        context: Context
    ) {
        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channelName = "message_channel"
        val channelId = "message_id"
        val channel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH)
        manager.createNotificationChannel(channel)
        val builder = NotificationCompat.Builder(context, channelId)
            .setContentTitle("title")
            .setContentText("desc")
            .setSmallIcon(R.drawable.icons_alarm)
        manager.notify(1,builder.build())
    }

}