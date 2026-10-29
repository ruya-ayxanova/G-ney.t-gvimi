
package com.turktakvimi.app

import android.app.Activity
import android.os.Bundle
import android.graphics.Color
import android.view.Gravity
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView

class MainActivity : Activity() {

    private val aylar = listOf(
        "آغلار گولر", "گولن آی", "قیزاران آی",
        "قورا بیشیرن", "قویروق دوغان", "زومار آیی",
        "خەزەل آیی", "قیروو آیی", "آذر",
        "چیله", "دوندران آی", "بایرام آیی"
    )

    private val gunler = listOf(
        "یئل لی گون", "سود گونو", "دوز گونو",
        "آرا گون", "اود گونو", "سو گونو", "آینی گونو"
    )

    private val renk = Color.rgb(38, 91, 66)
    private lateinit var icerik: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val ana = LinearLayout(this)
        ana.orientation = LinearLayout.VERTICAL
        ana.setBackgroundColor(Color.rgb(247, 244, 232))

        val baslik = TextView(this)
        baslik.text = "تورک تقویمی"
        baslik.textSize = 28f
        baslik.gravity = Gravity.CENTER
        baslik.setTextColor(renk)
        baslik.setPadding(12, 24, 12, 24)
        ana.addView(baslik)

        val dugmeler = LinearLayout(this)
        dugmeler.orientation = LinearLayout.HORIZONTAL

        val ayDugme = Button(this)
        ayDugme.text = "آی‌لیق تقویم"
        dugmeler.addView(
            ayDugme,
            LinearLayout.LayoutParams(0, -2, 1f)
        )

        val yilDugme = Button(this)
        yilDugme.text = "تاریخی گونلر"
        dugmeler.addView(
            yilDugme,
            LinearLayout.LayoutParams(0, -2, 1f)
        )

        ana.addView(dugmeler)

        val kaydir = ScrollView(this)
        icerik = LinearLayout(this)
        icerik.orientation = LinearLayout.VERTICAL
        icerik.setPadding(18, 12, 18, 24)
        kaydir.addView(icerik)
        ana.addView(
            kaydir,
            LinearLayout.LayoutParams(-1, 0, 1f)
        )

        ayDugme.setOnClickListener { aylariGoster() }
        yilDugme.setOnClickListener { yilDonumleriniGoster() }

        setContentView(ana)
        aylariGoster()
    }

    private fun yazi(metin: String, boyut: Float = 18f) {
        val tv = TextView(this)
        tv.text = metin
        tv.textSize = boyut
        tv.setTextColor(Color.DKGRAY)
        tv.setPadding(8, 10, 8, 10)
        icerik.addView(tv)
    }

    private fun aylariGoster() {
        icerik.removeAllViews()
        yazi("آیلار", 23f)

        aylar.forEachIndexed { index, ay ->
            yazi("${index + 1}. $ay")
        }

        yazi("هفته‌نین گونلری", 23f)
        gunler.forEachIndexed { index, gun ->
            yazi("${index + 1}. $gun")
        }
    }

    private fun yilDonumleriniGoster() {
        icerik.removeAllViews()
        yazi("تاریخی شخصیتلر", 23f)

        yazi("محمد امین رسول زاده")
        yazi("دوغوم: ۱۱ بهمن ۱۲۶۲")
        yazi("اؤلوم: ۱۶ اسفند ۱۳۳۳")

        yazi("مصطفی کمال آتاتورک")
        yazi("دوغوم گونو: ۱۹ مئی ۱۸۸۱")
        yazi("اؤلوم: ۱۰ نوامبر ۱۹۳۸")

        yazi("ابوالفضل ائلچی‌بی")
        yazi("دوغوم: ۳ تیر ۱۳۱۷")
        yazi("اؤلوم: ۱ شهریور ۱۳۷۹")

        yazi("تاریخی و فرهنگی بایرام‌لار", 23f)
        yazi("نوروز بایرامی")
        yazi("چیلله گئجه‌سی")
        yazi("۲۱ آذر — میلّی حکومتین یاد گونو")
    }
}
