package com.turktakvimi.app
import android.content.Intent
import android.app.Activity
import android.os.Bundle
import android.graphics.Typeface
import android.view.Gravity
import android.widget.Button
import android.widget.GridLayout
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView

class MainActivity : Activity() {

    private val months = listOf(
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

    private val weekdays = listOf(
        "یئل لی گون",
        "سود گونو",
        "دوز گونو",
        "آرا گون",
        "اود گونو",
        "سو گونو",
        "آینی گونو"
    )

    private var selectedMonth = 0
    private var selectedDay = 1

    private lateinit var root: LinearLayout
    private lateinit var monthTitle: TextView
    private lateinit var selectedTitle: TextView
    private lateinit var eventList: LinearLayout
    private lateinit var calendarGrid: GridLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val scroll = ScrollView(this)
        root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(16, 20, 16, 24)
            layoutDirection = LinearLayout.LAYOUT_DIRECTION_RTL
        }

        scroll.addView(root)
        setContentView(scroll)

        val title = textView(
            "تورک تقویمی",
            26f,
            true
        )
        root.addView(title)
val settingsButton = Button(this).apply {
    text = "تنظیمات"
    setOnClickListener {
        startActivityForResult(
            Intent(this@MainActivity, SettingsActivity::class.java),
            1001
        )
    }
}
root.addView(settingsButton)
        monthTitle = textView("", 22f, true)
        root.addView(monthTitle)

        val monthGrid = GridLayout(this).apply {
            columnCount = 3
        }

        months.forEachIndexed { index, name ->
            val button = Button(this).apply {
                text = name
                textSize = 13f
                setOnClickListener {
                    selectedMonth = index
                    selectedDay = 1
                    refreshCalendar()
                }
            }

            monthGrid.addView(
                button,
                GridLayout.LayoutParams().apply {
                    width = 0
                    columnSpec = GridLayout.spec(
                        GridLayout.UNDEFINED, 1f
                    )
                }
            )
        }

        root.addView(monthGrid)

        val weekGrid = GridLayout(this).apply {
            columnCount = 7
        }

        weekdays.forEach { dayName ->
            val label = textView(dayName, 11f, true)
            label.gravity = Gravity.CENTER
            weekGrid.addView(label)
        }

        root.addView(weekGrid)

        calendarGrid = GridLayout(this).apply {
            columnCount = 7
        }
        root.addView(calendarGrid)

        selectedTitle = textView("", 18f, true)
        root.addView(selectedTitle)

        val eventsTitle = textView(
            "بوتون مناسبتلر",
            22f,
            true
        )
        root.addView(eventsTitle)

        eventList = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
        }
        root.addView(eventList)

        refreshCalendar()
    }

    private fun refreshCalendar() {
        monthTitle.text = months[selectedMonth]

        calendarGrid.removeAllViews()

        for (day in 1..30) {
            val button = Button(this).apply {
                text = day.toString()
                textSize = 14f
                setOnClickListener {
                    selectedDay = day
                    showSelectedDay()
                }
            }

            calendarGrid.addView(
                button,
                GridLayout.LayoutParams().apply {
                    width = 0
                    height = GridLayout.LayoutParams.WRAP_CONTENT
                    columnSpec = GridLayout.spec(
                        GridLayout.UNDEFINED, 1f
                    )
                }
            )
        }

        showSelectedDay()
        showAllEvents()
    }

    private fun showSelectedDay() {
        selectedTitle.text =
            "${months[selectedMonth]} — $selectedDay"

        val todayEvents = Anniversaries.all.filter {
            it.monthIndex == selectedMonth &&
            it.day == selectedDay
        }

        if (todayEvents.isEmpty()) {
            selectedTitle.text =
                "${months[selectedMonth]} — $selectedDay\n" +
                "بو گون اوچون تاریخ تصدیق اولونماییب"
        }
    }

    private fun showAllEvents() {
        eventList.removeAllViews()

        Anniversaries.all.forEach { event ->
            val item = textView(
                "${event.name}\n" +
                "${event.type} — ${event.description}\n" +
                event.dateLabel,
                16f,
                false
            )

            item.setPadding(8, 12, 8, 12)
            eventList.addView(item)

            val line = TextView(this).apply {
                text = "ــــــــــــــــــــــــــــ"
                textSize = 12f
            }
            eventList.addView(line)
        }
    }

    private fun textView(
        value: String,
        size: Float,
        bold: Boolean
    ): TextView {
        return TextView(this).apply {
            text = value
            textSize = size
            gravity = Gravity.CENTER
            setPadding(4, 10, 4, 10)
            textDirection = TextView.TEXT_DIRECTION_RTL

            if (bold) {
                setTypeface(null, Typeface.BOLD)
            }
        }
    }
}
