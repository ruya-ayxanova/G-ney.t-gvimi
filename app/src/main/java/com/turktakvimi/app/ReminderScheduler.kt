package com.turktakvimi.app

import android.app.AlarmManager
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build

object ReminderScheduler {

    const val CHANNEL_ID = "turk_takvimi_events"

    fun createChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= 26) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "مهم مناسبتلر",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "مهم تقویم مناسبتلری"
            }

            val manager = context.getSystemService(
                Context.NOTIFICATION_SERVICE
            ) as NotificationManager

            manager.createNotificationChannel(channel)
        }
    }

    fun scheduleSameDay(
        context: Context,
        eventId: Int,
        title: String,
        description: String,
        triggerAtMillis: Long
    ) {
        if (!AppSettings.notificationsEnabled(context)) return
        if (triggerAtMillis <= System.currentTimeMillis()) return

        createChannel(context)

        val intent = Intent(
            context,
            EventReminderReceiver::class.java
        ).apply {
            putExtra("title", title)
            putExtra("description", description)
            putExtra("eventId", eventId)
        }

        val pending = PendingIntent.getBroadcast(
            context,
            eventId,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or
                PendingIntent.FLAG_IMMUTABLE
        )

        val alarm = context.getSystemService(
            Context.ALARM_SERVICE
        ) as AlarmManager

        alarm.setAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            triggerAtMillis,
            pending
        )
    }
}

class EventReminderReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if (!AppSettings.notificationsEnabled(context)) return

        if (
            Build.VERSION.SDK_INT >= 33 &&
            context.checkSelfPermission(
                android.Manifest.permission.POST_NOTIFICATIONS
            ) != android.content.pm.PackageManager.PERMISSION_GRANTED
        ) return

        ReminderScheduler.createChannel(context)

        val title = intent.getStringExtra("title") ?: "مهم مناسبت"
        val description =
            intent.getStringExtra("description") ?: ""

        val notification = if (Build.VERSION.SDK_INT >= 26) {
            Notification.Builder(context, ReminderScheduler.CHANNEL_ID)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle(title)
                .setContentText(description)
                .setAutoCancel(true)
                .build()
        } else {
            @Suppress("DEPRECATION")
            Notification.Builder(context)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle(title)
                .setContentText(description)
                .setAutoCancel(true)
                .build()
        }

        val manager = context.getSystemService(
            Context.NOTIFICATION_SERVICE
        ) as NotificationManager

        manager.notify(
            intent.getIntExtra("eventId", 1),
            notification
        )
    }
}
