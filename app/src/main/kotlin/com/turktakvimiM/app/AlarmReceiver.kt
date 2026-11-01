package com.turktakvimi.app

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        Notifier.show(
            context,
            intent.getStringExtra("t") ?: "",
            intent.getStringExtra("x") ?: ""
        )
    }
}
