package com.astra.koksharov.ui.calendar

import android.content.Context
import android.graphics.Color
import android.support.annotation.IntegerRes
import android.util.AttributeSet
import android.widget.Button
import android.widget.TextView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.LinearLayout
import com.astra.koksharov.astramultichosewithdatacalendar.R
import java.util.*

class CellButton(context: Context?, attrs: AttributeSet?) : Button(context, attrs) {//android.support.v7.widget.AppCompatButton LinearLayout ImageButton Button

    var chosen = false
        get() : Boolean {return field }
        set(value) {
            field = value
            if(chosen)
                backColor = Color.argb(127, 200, 200, 50)
            else
                backColor = Color.argb(127, 50, 100, 200)
            setBackgroundColor(backColor)
        }

    var backColor = Color.argb(127, 50, 100, 200)

    var date : Date? = null

    lateinit var main_tv: TextView
    lateinit var mark_tv: TextView

    var checked : Boolean = false
        set(value) {
            isSelected = value
            field = value
        }

    init {
        val layoutInflater = LayoutInflater.from(context)//context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = layoutInflater.inflate(R.layout.cell_button, null)//parent as (ViewGroup?)

//        view_group.add(view)z
//        icon = view.findViewById(R.id.xbutton2_icon) as ImageView

        main_tv = view.findViewById(R.id.cellMainTextView) as TextView
        mark_tv = view.findViewById(R.id.cellMarkTextView) as TextView

        setBackgroundColor(backColor)

        if (date == null){
            date = Calendar.getInstance().time
        }
//        val inflater = getSystemService(context) as (LayoutInflater);
        requestLayout()
        this.invalidate()
        this.refreshDrawableState()
        invalidate()
    }

    constructor(context: Context?, attrs: AttributeSet?, mainTxt: String, markTxt: String, date : Date) : this(context, attrs){
        CellButton(context, attrs)
        main_tv.text = mainTxt
        mark_tv.text = markTxt
        this.date = date

        requestLayout()
        this.invalidate()
        this.refreshDrawableState()
        invalidate()
    }

    public fun buttonClicked(chosenState : Boolean): Unit {
        main_tv.text = (Integer.parseInt(main_tv.text.toString()) + 1).toString();

        chosen = chosenState

        Log.i("<CELL BUTTON>", "MAIN:${main_tv.text} MARK:${mark_tv.text} Color:${backColor}")

        requestLayout()
    }



//    public fun CellButton(context: Context?, attrs: AttributeSet?, mainTxt: String, markTxt: String){

//    }

}