package com.astra.koksharov.ui.calendar

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.ListView
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import com.astra.koksharov.astramultichosewithdatacalendar.R
import android.view.ViewGroup
import java.util.*
import kotlin.collections.ArrayList


/**
 * TODO: document your custom view class.
 */
class AstraMultiChoseCalendar(context: Context?, attrs: AttributeSet? = null) : TableLayout(context, attrs) {
    var firstChosen = false
    var firstChosenButton : CellButton? = null
    var secondChosen = false
    var buttons = ArrayList<CellButton>()

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



//        tableRow.setBackgroundResource(R.drawable.mountain_fog)
        for (j in 1..20) {
            val tableRow = TableRow(context)

            this.addView(tableRow, TableLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));//

            rows.add(tableRow)

            tableRow.layoutParams = TableLayout.LayoutParams(
                TableLayout.LayoutParams.WRAP_CONTENT,
                TableLayout.LayoutParams.WRAP_CONTENT
            )

            var tv = TextView(context, attrs)
            tv.text = "May ${j}:"
            tableRow.addView(tv)
            for (i in 1..12) {
                this.setColumnShrinkable(i, true);

                var btn = CellButton(context, attrs, "0", "${i}", Calendar.getInstance().time)
//            btn.layoutParams = ViewGroup.LayoutParams(100, 100)
                btn.setOnClickListener(View.OnClickListener {
                    if (!firstChosen) {
                        btn.buttonClicked(true)
                        firstChosen = true
                        firstChosenButton = btn
                    } else if (!secondChosen) {
                        secondChosen = true
                        if (firstChosenButton == btn)
                            btn.buttonClicked(false)
                        else {
                            var colored = 0
                            for (btni in buttons) {
                                if (btni == btn || btni == firstChosenButton) {
                                    btni.chosen = true
                                    colored++
                                } else btni.chosen = colored == 1
                            }
                        }
                    } else {
                        firstChosen = false
                        secondChosen = false
                        for (btni in buttons) {
                            btni.chosen = false
                        }
                        btn.buttonClicked(false)
                    }

                })
                tableRow.addView(btn)
                buttons.add(btn)
            }
        }


        Log.i("AMCC CALENDAR", "init")
//
//        val tableLayout = findViewById<AstraMultiChoseCalendar>(
//            R.id.astra_multi_chose_table_layout) as TableLayout

//        setContentView(R.id.astra_multi_chose_table_layout)
        exampleString = resources.getString(R.string.app_name)

    }




}
