package com.turktakvimi.app

import android.app.AlarmManager
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import org.json.JSONArray

object Notifier {
    const val CHANNEL = "gunluk"
    const val PREFS = "notif"
    const val KEY = "items"
    private const val BASE = 5000

    fun ensureChannel(c: Context) {
        if (Build.VERSION.SDK_INT >= 26) {
            val nm = c.getSystemService(NotificationManager::class.java)
            nm.createNotificationChannel(
                NotificationChannel(CHANNEL, "Günlük bildiriş", NotificationManager.IMPORTANCE_DEFAULT)
            )
        }
    }

    private fun pending(c: Context, i: Int, title: String?, text: String?): PendingIntent {
        val intent = Intent(c, AlarmReceiver::class.java)
            .putExtra("t", title)
            .putExtra("x", text)
        return PendingIntent.getBroadcast(
            c, BASE + i, intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
    }

    fun schedule(c: Context, json: String) {
        val am = c.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val prefs = c.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
        val old = JSONArray(prefs.getString(KEY, "[]") ?: "[]")
        for (i in 0 until old.length()) am.cancel(pending(c, i, null, null))
        prefs.edit().putString(KEY, json).apply()
        val arr = JSONArray(json)
        val now = System.currentTimeMillis()
        for (i in 0 until arr.length()) {
            val o = arr.getJSONObject(i)
            val at = o.getLong("at")
            if (at <= now) continue
            am.setAndAllowWhileIdle(
                AlarmManager.RTC_WAKEUP, at,
                pending(c, i, o.getString("t"), o.getString("x"))
            )
        }
    }

    @Suppress("DEPRECATION")
    fun show(c: Context, title: String, text: String) {
        ensureChannel(c)
        val open = PendingIntent.getActivity(
            c, 1, Intent(c, MainActivity::class.java),
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )
        val b = if (Build.VERSION.SDK_INT >= 26) Notification.Builder(c, CHANNEL) else Notification.Builder(c)
        b.setSmallIcon(android.R.drawable.ic_menu_my_calendar)
            .setContentTitle(title)
            .setContentText(text)
            .setStyle(Notification.BigTextStyle().bigText(text))
            .setContentIntent(open)
            .setAutoCancel(true)
        val nm = c.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        nm.notify((System.currentTimeMillis() / 1000).toInt(), b.build())
    }
}
