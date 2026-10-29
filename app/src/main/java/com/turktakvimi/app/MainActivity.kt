
package com.turktakvimi.app

import android.app.Activity
import android.os.Bundle
import android.graphics.Color
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : Activity() {

    private val aylar = listOf(
        "Ağlar Gülər", "Gülən Ay", "Qızaran Ay",
        "Qura Bişirən", "Quyruq Doğan", "Zumar Ayı",
        "Xəzəl Ayı", "Qırov Ayı", "Azər",
        "Çillə", "Donduran Ay", "Bayram Ayı"
    )

    private val gunler = listOf(
        "Yel-li Gün", "Süd Günü", "Duz Günü",
        "Ara Gün", "Od Günü", "Su Günü", "Ayni Günü"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val ekran = LinearLayout(this)
        ekran.orientation = LinearLayout.VERTICAL
        ekran.gravity = Gravity.CENTER
        ekran.setPadding(24, 24, 24, 24)
        ekran.setBackgroundColor(Color.rgb(247, 244, 232))

        val basliq = TextView(this)
        basliq.text = "TÜRK TƏQVİMİ"
        basliq.textSize = 28f
        basliq.setTextColor(Color.rgb(35, 90, 65))
        basliq.gravity = Gravity.CENTER
        ekran.addView(basliq)

        val altBasliq = TextView(this)
        altBasliq.text = "\nAylar"
        altBasliq.textSize = 22f
        altBasliq.setTextColor(Color.BLACK)
        ekran.addView(altBasliq)

        aylar.forEachIndexed { index, ay ->
            val metin = TextView(this)
            metin.text = "${index + 1}. $ay"
            metin.textSize = 18f
            metin.setTextColor(Color.DKGRAY)
            metin.setPadding(8, 6, 8, 6)
            ekran.addView(metin)
        }

        val gunBasliq = TextView(this)
        gunBasliq.text = "\nHəftənin günləri"
        gunBasliq.textSize = 22f
        gunBasliq.setTextColor(Color.BLACK)
        ekran.addView(gunBasliq)

        gunler.forEachIndexed { index, gun ->
            val metin = TextView(this)
            metin.text = "${index + 1}. $gun"
            metin.textSize = 17f
            metin.setTextColor(Color.DKGRAY)
            ekran.addView(metin)
        }

        setContentView(ekran)
    }
}
