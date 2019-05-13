package com.astra.koksharov.ui.calendar

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.util.Log
import android.widget.ListView
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import com.astra.koksharov.astramultichosewithdatacalendar.R

/**
 * TODO: document your custom view class.
 */
class AstraMultiChoseCalendar(context: Context?, attrs: AttributeSet? = null) : TableLayout(context, attrs) {

//    private lateinit var listView : ListView
    private var rows = ArrayList<TableRow>()

    private var context1: Context? = null

    var exampleString: String? = null // TODO: use a default from R.string...

    var exampleColor: Int = Color.RED // TODO: use a default from R.color...
    var exampleDimension: Float = 0f // TODO: use a default from R.dimen...

    init{
        this.context1 = context

        setStretchAllColumns(true);
//        addContent(data);


        val tableRow = TableRow(context)

        this.addView(tableRow, TableLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));//

        rows.add(tableRow)

        tableRow.layoutParams = TableLayout.LayoutParams(
            TableLayout.LayoutParams.MATCH_PARENT,
            TableLayout.LayoutParams.WRAP_CONTENT
        )
//        tableRow.setBackgroundResource(R.drawable.mountain_fog)
        var tv = TextView(context, attrs)
        tv.text = "sddaads"
        tableRow.addView(tv)

        tableRow.addView(CellButton(context, attrs, "1", "2"))

        Log.i("AMCC CALENDAR", "init")
//
//        val tableLayout = findViewById<AstraMultiChoseCalendar>(
//            R.id.astra_multi_chose_table_layout) as TableLayout

//        setContentView(R.id.astra_multi_chose_table_layout)
        exampleString = resources.getString(R.string.app_name)

    }



}
