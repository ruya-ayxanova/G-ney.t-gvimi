
package com.turktakvimi.app

import android.content.Context
import android.content.res.Configuration

object AppSettings {

    private const val PREFS = "turk_takvimi_settings"

    const val MODE_AUTO = "auto"
    const val MODE_LIGHT = "light"
    const val MODE_DARK = "dark"

    fun save(
        context: Context,
        mode: String,
        fontSize: Int,
        notifications: Boolean
    ) {
        context.getSharedPreferences(
            PREFS, Context.MODE_PRIVATE
        ).edit()
            .putString("mode", mode)
            .putString("theme", "green_cream")
            .putInt("font_size", fontSize)
            .putBoolean("notifications", notifications)
            .putString("reminder", "same_day")
            .apply()
    }

    fun mode(context: Context): String =
        context.getSharedPreferences(
            PREFS, Context.MODE_PRIVATE
        ).getString("mode", MODE_AUTO) ?: MODE_AUTO

    fun fontSize(context: Context): Int =
        context.getSharedPreferences(
            PREFS, Context.MODE_PRIVATE
        ).getInt("font_size", 18)

    fun notificationsEnabled(context: Context): Boolean =
        context.getSharedPreferences(
            PREFS, Context.MODE_PRIVATE
        ).getBoolean("notifications", true)

    fun isDark(context: Context): Boolean {
        return when (mode(context)) {
            MODE_DARK -> true
            MODE_LIGHT -> false
            else -> {
                val nightMode =
                    context.resources.configuration.uiMode and
                    Configuration.UI_MODE_NIGHT_MASK

                nightMode == Configuration.UI_MODE_NIGHT_YES
            }
        }
    }
}
