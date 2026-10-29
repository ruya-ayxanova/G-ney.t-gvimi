package com.turktakvimi.app

data class Anniversary(
    val name: String,
    val type: String,
    val description: String,
    val dateLabel: String = "تاریخ تعیین اولوناجاق",
    val monthIndex: Int? = null,
    val day: Int? = null
)

object Anniversaries {

    val all = listOf(
        Anniversary(
            "محمد امین رسول زاده",
            "تاریخی شخصیت",
            "دوغوم گونو"
        ),
        Anniversary(
            "محمد امین رسول زاده",
            "تاریخی شخصیت",
            "اؤلوم گونو"
        ),
        Anniversary(
            "مصطفی کمال آتاتورک",
            "تاریخی شخصیت",
            "دوغوم گونو"
        ),
        Anniversary(
            "مصطفی کمال آتاتورک",
            "تاریخی شخصیت",
            "اؤلوم گونو"
        ),
        Anniversary(
            "ابوالفضل ائلچی‌بی",
            "تاریخی شخصیت",
            "دوغوم گونو"
        ),
        Anniversary(
            "ابوالفضل ائلچی‌بی",
            "تاریخی شخصیت",
            "اؤلوم گونو"
        ),
        Anniversary(
            "ستارخان",
            "تاریخی شخصیت",
            "یاد گونو"
        ),
        Anniversary(
            "باقرخان",
            "تاریخی شخصیت",
            "یاد گونو"
        ),
        Anniversary(
            "شیخ محمد خیابانی",
            "تاریخی شخصیت",
            "یاد گونو"
        ),
        Anniversary(
            "تبریز مشروطه‌سی",
            "تاریخی",
            "مشروطه حرکاتی"
        ),
        Anniversary(
            "۲۱ آذر",
            "تاریخی",
            "آذربایجان میلّی حکومتی"
        ),
        Anniversary(
            "نوروز بایرامی",
            "میلّی و فرهنگی",
            "باهار و یئنی ایل بایرامی"
        ),
        Anniversary(
            "چهارشنبه‌سوری",
            "فرهنگی",
            "باهار قارشیلاماسی"
        ),
        Anniversary(
            "چیلله گئجه‌سی",
            "فرهنگی",
            "قیشین اوزون گئجه‌سی"
        ),
        Anniversary(
            "مهرگان",
            "فرهنگی",
            "کهن بایرام"
        ),
        Anniversary(
            "رمضان",
            "دینی",
            "اوروج آیی"
        ),
        Anniversary(
            "قدر گئجه‌سی",
            "دینی",
            "قدر گئجه‌سی"
        ),
        Anniversary(
            "عید فطر",
            "دینی",
            "رمضان بایرامی"
        ),
        Anniversary(
            "عید قربان",
            "دینی",
            "قربان بایرامی"
        ),
        Anniversary(
            "غدیر خم",
            "دینی",
            "غدیر گونو"
        ),
        Anniversary(
            "تاسوعا",
            "دینی",
            "محرم آیی"
        ),
        Anniversary(
            "عاشورا",
            "دینی",
            "محرم آیی"
        ),
        Anniversary(
            "اربعین",
            "دینی",
            "اربعین گونو"
        ),
        Anniversary(
            "میلاد پیامبر",
            "دینی",
            "مولود گونو"
        ),
        Anniversary(
            "میلاد امام علی",
            "دینی",
            "۱۳ رجب"
        ),
        Anniversary(
            "نیمه شعبان",
            "دینی",
            "۱۵ شعبان"
        )
    )
}
