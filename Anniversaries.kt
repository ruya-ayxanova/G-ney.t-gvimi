
package com.turktakvimi.app

data class Anniversary(
    val name: String,
    val shamsiDate: String,
    val type: String,
    val description: String,
    val year: Int? = null
)

object Anniversaries {

    val all = listOf(
        Anniversary(
            "محمد امین رسول زاده",
            "۱۱ بهمن",
            "تاریخی شخصیت",
            "دوغوم گونو",
            1884
        ),
        Anniversary(
            "محمد امین رسول زاده",
            "۱۶ اسفند",
            "تاریخی شخصیت",
            "اؤلوم گونو",
            1955
        ),
        Anniversary(
            "مصطفی کمال آتاتورک",
            "۲۹ اردیبهشت",
            "تاریخی شخصیت",
            "سمبولیک دوغوم گونو",
            1881
        ),
        Anniversary(
            "ابوالفضل ائلچی‌بی",
            "۳ تیر",
            "تاریخی شخصیت",
            "دوغوم گونو",
            1938
        ),
        Anniversary(
            "ابوالفضل ائلچی‌بی",
            "۱ شهریور",
            "تاریخی شخصیت",
            "اؤلوم گونو",
            2000
        ),
        Anniversary(
            "نوروز بایرامی",
            "۱ بایرام آیی",
            "میلّی و فرهنگی",
            "یئنی ایل و باهار بایرامی"
        ),
        Anniversary(
            "چیلله گئجه‌سی",
            "۳۰ چیله",
            "فرهنگی",
            "قیشین اوزون گئجه‌سی"
        )
    )
}
