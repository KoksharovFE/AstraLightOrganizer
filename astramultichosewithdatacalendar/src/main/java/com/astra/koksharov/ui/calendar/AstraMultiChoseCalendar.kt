package com.astra.koksharov.ui.calendar

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import com.astra.koksharov.astramultichosewithdatacalendar.R
import android.view.ViewGroup
import android.widget.*
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList




/**
 * TODO: document your custom view class.
 */
class AstraMultiChoseCalendar(context: Context?, attrs: AttributeSet? = null) : TableLayout(context, attrs) {
    var firstChosen = false
    var firstChosenButton : RelativeLayoutButton? = null //CellButton? = null
    var secondChosen = false
    var buttons = ArrayList<RelativeLayoutButton>()//CellButton

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
        val layoutInflater = LayoutInflater.from(context)//context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = layoutInflater.inflate(R.layout.astra_multi_chose_calendar, null)//parent as (ViewGroup?)
        val scrollView = view.findViewById<ScrollView>(R.id.astra_multi_chose_table_scroll)
//        this.setText

    //astra_multi_chose_table_scroll

//        tableRow.setBackgroundResource(R.drawable.mountain_fog)
        var calMY = Calendar.getInstance()
//        calMY.set(Calendar.DAY_OF_MONTH, calMY.get(Calendar.DAY_OF_MONTH) -1 )
        calMY.set(Calendar.YEAR, calMY.get(Calendar.YEAR) -1 )
        var calPY = Calendar.getInstance()
//        calPY.set(Calendar.DAY_OF_MONTH, calPY.get(Calendar.DAY_OF_MONTH) +1 )
        calPY.set(Calendar.YEAR, calPY.get(Calendar.YEAR) +1 )



        for (i in 1..7){
            this.setColumnShrinkable(i, true)
        }

        var counter = 0;
        var tableRow = TableRow(context)//


        run{
//            tableRow = TableRow(context)
//
//            this.addView(
//                tableRow,
//                LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
//            );//
//            tableRow
//            rows.add(tableRow)
//
//            tableRow.layoutParams = LayoutParams(
//                LayoutParams.WRAP_CONTENT,
//                LayoutParams.WRAP_CONTENT
//            )
//
//            for (i in 0..6){
//                var btn = Button(context,attrs)
//                btn.setText("${i}")
//                tableRow.addView(btn)
//            }
        }

        while (calMY.before(calPY)) {//1..20

            val result = calMY.getTime()
            calMY.add(Calendar.DATE, 1)

            if (counter % 7 == 0) {
                tableRow = TableRow(context)

                this.addView(
                    tableRow,
                    LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
                );//

                rows.add(tableRow)

                tableRow.layoutParams = LayoutParams(
                    LayoutParams.WRAP_CONTENT,
                    LayoutParams.WRAP_CONTENT
                )

                var tv = TextView(context, attrs)
                tv.text = "${calMY.get(Calendar.YEAR)} / ${calMY.get(Calendar.MONTH) + 1}:"//  ${calMY.get(Calendar.DAY_OF_MONTH)}
                tableRow.addView(tv)

            }
//            for (i in 1..12) {

            var btn = RelativeLayoutButton(context!!)//CellButton(context, attrs, "0", "${counter}", result)
            btn.setMainText(calMY.get(Calendar.DAY_OF_MONTH).toString());
            btn.setMarkText("0")

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
                            btn.buttonClicked(true)
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

                counter++
//            }

            scrollView.scrollTo(10000,  100)//scrollView.maxScrollAmount / 2

        }


        Log.i("AMCC CALENDAR", "init")
//
//        val tableLayout = findViewById<AstraMultiChoseCalendar>(
//            R.id.astra_multi_chose_table_layout) as TableLayout

//        setContentView(R.id.astra_multi_chose_table_layout)
        exampleString = resources.getString(R.string.app_name)

    }




}
