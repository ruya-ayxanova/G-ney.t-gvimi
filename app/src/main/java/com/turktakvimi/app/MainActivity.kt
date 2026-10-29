
package com.turktakvimi.app

import android.app.Activity
import android.os.Bundle
import android.graphics.Color
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : Activity() {

    
private val aylar = listOf(
    "آغلار گولر",
    "گولن آی",
    "قیزاران آی",
    "قورا بیشیرن",
    "قویروق دوغان",
    "زومار آیی",
    "خەزەل آیی",
    "قیروو آیی",
    "آذر",
    "چیله",
    "دوندران آی",
    "بایرام آیی"
)

private val gunler = listOf(
    "یئل لی گون",
    "سود گونو",
    "دوز گونو",
    "آرا گون",
    "اود گونو",
    "سو گونو",
    "آینی گونو"
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
