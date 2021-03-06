package com.astra.koksharov.ui.calendar

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import com.astra.koksharov.astramultichosewithdatacalendar.R
import android.widget.*
import kotlinx.android.synthetic.main.sample_calendar_cell.view.*
import java.util.*
import kotlin.collections.ArrayList




/**
 * TODO: document your custom view class.
 */
class AstraMultiChoseCalendar(context: Context?, attrs: AttributeSet? = null) : TableLayout(context, attrs) {
    var firstChosen = false
    var firstChosenButton : AstraRLCellButton? = null //CellButton? = null
    var secondChosen = false
    var buttons = ArrayList<AstraRLCellButton>()//CellButton

//    private lateinit var listView : ListView
    private var rows = ArrayList<TableRow>()

    private var context1: Context? = null

    var exampleString: String? = null // TODO: use a default from R.string...

    var exampleColor: Int = Color.RED // TODO: use a default from R.color...
    var exampleDimension: Float = 0f // TODO: use a default from R.dimen...

    init{
        this.context1 = context

        setStretchAllColumns(true)
//        addContent(data);
        val layoutInflater = LayoutInflater.from(context)//context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = layoutInflater.inflate(R.layout.astra_multi_chose_calendar, null)//parent as (ViewGroup?)
        val scrollView = view.findViewById<ScrollView>(R.id.astra_multi_chose_table_scroll)
//        this.setText

    //astra_multi_chose_table_scroll

//        tableRow.setBackgroundResource(R.drawable.mountain_fog)

        var earliestDate = Calendar.getInstance()
//        calMY.set(Calendar.DAY_OF_MONTH, calMY.get(Calendar.DAY_OF_MONTH) -1 )
        earliestDate.set(Calendar.YEAR, earliestDate.get(Calendar.YEAR) -1 )
        var latestDate = Calendar.getInstance()
//        calPY.set(Calendar.DAY_OF_MONTH, calPY.get(Calendar.DAY_OF_MONTH) +1 )
        latestDate.set(Calendar.YEAR, latestDate.get(Calendar.YEAR) +1 )

        setBaseData(scrollView, attrs, earliestDate, latestDate)

        Log.i("AMCC CALENDAR", "init")
//
//        val tableLayout = findViewById<AstraMultiChoseCalendar>(
//            R.id.astra_multi_chose_table_layout) as TableLayout

//        setContentView(R.id.astra_multi_chose_table_layout)
        exampleString = resources.getString(R.string.app_name)

    }

    private fun setBaseData( scrollView: ScrollView, attrs: AttributeSet? = null,
                             earliestDate : Calendar, latestDate : Calendar){

        var calMY = earliestDate

        for (i in 0..10){
            this.setColumnShrinkable(i, true)
        }

        //region new gen
        var tableRow = TableRow(context)//

        //Выделение каждого месяца
        //Прядок по дням недели по-умолчанию начиная с воскресенья
        var lastMonth: Int = Int.MIN_VALUE

        while (calMY.before(latestDate)) {
            val year = calMY.get(Calendar.YEAR)
            val month = calMY.get(Calendar.MONTH)
            val currentDate = calMY.getTime()

            if (lastMonth != month) {
                //region новая строка с месяцем в надписи
                tableRow = TableRow(context)
                var tv = TextView(context, attrs)
                tv.text = "${calMY.get(Calendar.YEAR)}.${calMY.get(Calendar.MONTH) + 1}:"//  ${calMY.get(Calendar.DAY_OF_MONTH)}


                this.addView(
                    tableRow,
                    LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
                )
                tableRow.addView(tv)//, 7.0f
                //endregion

                //region новая строка с добавлением пустот в начале
                tableRow = TableRow(context)
                this.addView(
                    tableRow,
                    LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
                )
                val dayOfWeek = calMY.get(Calendar.DAY_OF_WEEK)
                val elementsNums = tableRow.childCount
                for (i in 0..(dayOfWeek - elementsNums)){
                    tableRow.addView(AstraRLDummyCell(context1), LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT))//, 1.0f
                }
                //endregion

            }

            val dayOfWeek = calMY.get(Calendar.DAY_OF_WEEK)
            val elementsNums = tableRow.childCount
            if (elementsNums > dayOfWeek){
                tableRow = TableRow(context)
                this.addView(
                    tableRow,
                    LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
                )
            }

            var btn = AstraRLCellButton(context!!, calMY)//CellButton(context, attrs, "0", "${counter}", result)
            setBtnListener(btn)

            tableRow.addView(btn)
            buttons.add(btn)
            //tableRow.addView(btn)//calMY.get(Calendar.DAY_OF_WEEK)

            lastMonth = month

            calMY.add(Calendar.DATE, 1)
        }
        //endregion

        //region old gen
        //        while (calMY.before(latestDate)) {//1..20
//
//            val result = calMY.getTime()
//            calMY.add(Calendar.DATE, 1)
//            if (counter % 7 == 0) {
//                tableRow = TableRow(context)
//
//                this.addView(
//                    tableRow,
//                    LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
//                );//
//
//                rows.add(tableRow)
//
//                tableRow.layoutParams = LayoutParams(
//                    LayoutParams.WRAP_CONTENT,
//                    LayoutParams.WRAP_CONTENT
//                )
//
//                var tv = TextView(context, attrs)
//                tv.text = "${calMY.get(Calendar.YEAR)} / ${calMY.get(Calendar.MONTH) + 1}:"//  ${calMY.get(Calendar.DAY_OF_MONTH)}
//                tableRow.addView(tv)
//
//            }
//
//            var btn = AstraRLCellButton(context!!, calMY)//CellButton(context, attrs, "0", "${counter}", result)
//            setBtnListener(btn)
//
//            tableRow.addView(btn)
//            buttons.add(btn)
//
//            counter++
//
//            scrollView.scrollTo(20000,  100)//scrollView.maxScrollAmount / 2
//
//        }
        //endregion
    }

    private fun setBtnListener(btn : AstraRLCellButton){
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
    }

    fun getSelectedDates() : ArrayList<Calendar>{
        var selectedDates = ArrayList<Calendar>()
        for (btn in buttons){
            if (btn.chosen)
                selectedDates.add(btn.calendar)
//            selectedDates.add(btn.)
        }
        return selectedDates
    }

}
