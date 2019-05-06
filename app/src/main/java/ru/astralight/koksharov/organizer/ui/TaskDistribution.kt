package ru.astralight.koksharov.organizer.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.astra.koksharov.astralightorganizer.R
import android.util.Log
import android.view.View
import android.widget.Spinner
import com.savvi.rangedatepicker.CalendarPickerView
import java.util.*


class TaskDistribution : AppCompatActivity() {

    lateinit var spinner: Spinner
    lateinit var calendar : CalendarPickerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_distribution)
        val intent = intent
        val name = intent.getStringExtra("name")
        Log.d("TASK DISTR NAME", "${name}")

        val rightNowPlusYear = Calendar.getInstance()
        rightNowPlusYear.set(rightNowPlusYear.get(Calendar.YEAR) + 1,
            rightNowPlusYear.get(Calendar.MONTH),
            rightNowPlusYear.get(Calendar.DAY_OF_MONTH))

        calendar = findViewById<View>(R.id.task_distribution_calendar_view) as CalendarPickerView

        calendar.init(Date(),
            Date( rightNowPlusYear.timeInMillis )
            ) //
            .inMode(CalendarPickerView.SelectionMode.RANGE)
            .withSelectedDate(Date())
            // deactivates given dates, non selectable
//            .withDeactivateDates(list)
            // highlight dates in red color, mean they are aleady used.
//            .withHighlightedDates(arrayList)

    }


    fun getCalendarData() : List<Date>{
        return calendar.getSelectedDates()
    }
}
