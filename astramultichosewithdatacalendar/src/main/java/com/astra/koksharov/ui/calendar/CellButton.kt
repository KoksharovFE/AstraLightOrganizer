package com.astra.koksharov.ui.calendar

import android.content.Context
import android.util.AttributeSet
import android.widget.Button
import android.widget.TextView
import android.support.v4.content.ContextCompat.getSystemService
import android.view.LayoutInflater
import com.astra.koksharov.astramultichosewithdatacalendar.R

class CellButton(context: Context?, attrs: AttributeSet?) : android.support.v7.widget.AppCompatButton(context, attrs) {

    lateinit var main_tv: TextView
    lateinit var mark_tv: TextView
    var checked : Boolean = false
        set(value) {
            isSelected = value
            field = value
        }

    init {
        val layoutInflater = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = layoutInflater.inflate(R.layout.cell_button, null)
//        icon = view.findViewById(R.id.xbutton2_icon) as ImageView
        main_tv = view.findViewById(R.id.cellMainTextView) as TextView
        mark_tv = view.findViewById(R.id.cellMarkTextView) as TextView
    }

    constructor(context: Context?, attrs: AttributeSet?, mainTxt: String, markTxt: String) : this(context, attrs){
        CellButton(context, attrs)
        main_tv.text = mainTxt
        mark_tv.text = markTxt
    }

//    public fun CellButton(context: Context?, attrs: AttributeSet?, mainTxt: String, markTxt: String){

//    }

}