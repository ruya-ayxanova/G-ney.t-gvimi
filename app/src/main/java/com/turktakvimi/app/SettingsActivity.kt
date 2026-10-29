
package com.turktakvimi.app

import android.app.Activity
import android.os.Bundle
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Build
import android.view.Gravity
import android.widget.Button
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.SeekBar
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast

class SettingsActivity : Activity() {

    private val green = Color.rgb(28, 67, 49)
    private val cream = Color.rgb(248, 241, 220)

    private lateinit var modeGroup: RadioGroup
    private lateinit var fontLabel: TextView
    private lateinit var fontSeek: SeekBar
    private lateinit var notificationSwitch: Switch

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dark = AppSettings.isDark(this)
        val bg = if (dark) green else cream
        val fg = if (dark) cream else green

        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER_HORIZONTAL
            setPadding(24, 24, 24, 24)
            setBackgroundColor(bg)
        }

        fun label(text: String, size: Float): TextView {
            return TextView(this).apply {
                this.text = text
                textSize = size
                setTextColor(fg)
                setPadding(4, 12, 4, 12)
            }
        }

        root.addView(label("تنظیمات", 26f))
        root.addView(label("گؤرونوش مودو", 18f))

        modeGroup = RadioGroup(this).apply {
            orientation = RadioGroup.VERTICAL
        }

        val auto = RadioButton(this).apply {
            id = 1
            text = "اوتوماتیک"
        }
        val light = RadioButton(this).apply {
            id = 2
            text = "گوندوز مودو"
        }
        val darkButton = RadioButton(this).apply {
            id = 3
            text = "گئجه مودو"
        }

        listOf(auto, light, darkButton).forEach {
            it.setTextColor(fg)
            modeGroup.addView(it)
        }

        modeGroup.check(
            when (AppSettings.mode(this)) {
                AppSettings.MODE_LIGHT -> 2
                AppSettings.MODE_DARK -> 3
                else -> 1
            }
        )

        root.addView(modeGroup)

        root.addView(label("رنگ تمی: تیره یئشیل - کرم", 18f))

        fontLabel = label(
            "یازی بؤیوکلویو: ${AppSettings.fontSize(this)}",
            18f
        )
        root.addView(fontLabel)

        fontSeek = SeekBar(this).apply {
            max = 12
            progress = AppSettings.fontSize(this@SettingsActivity) - 14
        }

        fontSeek.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(
                    seekBar: SeekBar?,
                    progress: Int,
                    fromUser: Boolean
                ) {
                    fontLabel.text = "یازی بؤیوکلویو: ${progress + 14}"
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {}
                override fun onStopTrackingTouch(seekBar: SeekBar?) {}
            }
        )

        root.addView(fontSeek)

        notificationSwitch = Switch(this).apply {
            text = "مهم مناسبتلر اوچون بیلدیریشلر"
            setTextColor(fg)
            isChecked = AppSettings.notificationsEnabled(this@SettingsActivity)
        }
        root.addView(notificationSwitch)

        root.addView(label("بیلدیریش: مناسبت گونو", 16f))

        val saveButton = Button(this).apply {
            text = "ی Yadda saxla"
            setOnClickListener { saveSettings() }
        }
        root.addView(saveButton)

        setContentView(root)
    }

    private fun saveSettings() {
        val mode = when (modeGroup.checkedRadioButtonId) {
            2 -> AppSettings.MODE_LIGHT
            3 -> AppSettings.MODE_DARK
            else -> AppSettings.MODE_AUTO
        }

        val enabled = notificationSwitch.isChecked

        if (
            enabled &&
            Build.VERSION.SDK_INT >= 33 &&
            checkSelfPermission(
                android.Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissions(
                arrayOf(android.Manifest.permission.POST_NOTIFICATIONS),
                100
            )
            return
        }

        saveValues(mode, enabled)
    }

    private fun saveValues(mode: String, enabled: Boolean) {
        AppSettings.save(
            this,
            mode,
            fontSeek.progress + 14,
            enabled
        )

        Toast.makeText(
            this,
            "Ayarlar saxlanıldı",
            Toast.LENGTH_SHORT
        ).show()

        setResult(Activity.RESULT_OK)
        finish()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(
            requestCode, permissions, grantResults
        )

        if (requestCode == 100) {
            val granted =
                grantResults.isNotEmpty() &&
                grantResults[0] == PackageManager.PERMISSION_GRANTED

            val mode = when (modeGroup.checkedRadioButtonId) {
                2 -> AppSettings.MODE_LIGHT
                3 -> AppSettings.MODE_DARK
                else -> AppSettings.MODE_AUTO
            }

            saveValues(mode, notificationSwitch.isChecked && granted)
        }
    }
}
