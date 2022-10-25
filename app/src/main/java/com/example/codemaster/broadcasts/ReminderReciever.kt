package com.example.codemaster.broadcasts

import android.app.Notification.BADGE_ICON_LARGE
import android.app.Notification.VISIBILITY_PRIVATE
import android.app.Notification.VISIBILITY_PUBLIC
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.Ringtone
import android.media.RingtoneManager
import android.media.RingtoneManager.getDefaultUri
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.content.res.ResourcesCompat
import com.example.codemaster.R


class ReminderReciever : BroadcastReceiver() {
    lateinit var notificationManager: NotificationManager
    companion object {
        lateinit var ringtoneSound: Ringtone
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onReceive(
        context: Context,
        intent: Intent
    ) {

        notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        createAlarmChannel()

        val platformName = intent.getStringExtra("platform")
        val contestName = intent.getStringExtra("contest")
        val alarmId = intent.getIntExtra("alarmId",0)
        Log.d("kalu", "reminder $alarmId")

        val ringLength = intent.getIntExtra("ringLength", 5)
//        val contentIntent = Intent(context, MainActivity::class.java)
//
//        val contentPendingIntent = PendingIntent.getActivity(
//            context, alarmId,contentIntent,PendingIntent.FLAG_IMMUTABLE
//        )

        val deleteIntent = Intent(context, CancelNotificationReciever::class.java).putExtra("alarm", alarmId)
        val deletePendingIntent = PendingIntent.getBroadcast(context,
            alarmId,deleteIntent,PendingIntent.FLAG_IMMUTABLE)

        val builder = NotificationCompat.Builder(context, "ALARM_CHANNEL_ID")
            .setSmallIcon(R.drawable.icons_alarm)
            .setContentTitle(platformName)
            .setContentText(contestName)
            .setColor(ResourcesCompat.getColor(context.resources, R.color.teal_200, null))
//            .setContentIntent(contentPendingIntent)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setCategory(NotificationCompat.CATEGORY_ALARM)
            .setAllowSystemGeneratedContextualActions(false)
            .setVisibility(VISIBILITY_PUBLIC)
            .addAction(R.drawable.icons_alarm, "STOP", deletePendingIntent)
            .setBadgeIconType(BADGE_ICON_LARGE)
            .setOngoing(true)
            .setAutoCancel(true)

        notificationManager.notify(alarmId, builder.build())
        // when notification fired, Ringtone starts.
        ringtoneSound = RingtoneManager.getRingtone(context, getDefaultUri(RingtoneManager.TYPE_ALARM))

        if(ringtoneSound.isPlaying){
            ringtoneSound.stop()
        } else {
            ringtoneSound.play()
        }
        // and after certain seconds, Ringtone will stop.
//        val countLength = 1000 * ringLength.toLong()
//        object : CountDownTimer(countLength, 1000) {
//
//            override fun onTick(millisUntilFinished: Long) {}
//
//            override fun onFinish() {
//                ringtoneSound.stop()
//                notificationManager.cancel(0)
//            }
//        }.start()
    }

    private fun createAlarmChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val alarmChannel = NotificationChannel("ALARM_CHANNEL_ID", "Alarm", NotificationManager.IMPORTANCE_HIGH)
            alarmChannel.setSound(null, null)
            alarmChannel.enableLights(true)
            alarmChannel.lightColor = R.color.purple_500
            alarmChannel.enableVibration(true)
            alarmChannel.description = "Alarm style"
            alarmChannel.lockscreenVisibility = VISIBILITY_PRIVATE
            notificationManager.createNotificationChannel(alarmChannel)
        }
    }
}