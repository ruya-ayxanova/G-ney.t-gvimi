package com.turktakvimi.app

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class BootReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val json = context.getSharedPreferences(Notifier.PREFS, Context.MODE_PRIVATE)
            .getString(Notifier.KEY, "[]") ?: "[]"
        Notifier.schedule(context, json)
    }
}
