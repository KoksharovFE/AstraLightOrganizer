package com.astra.koksharov.ui.calendar

import android.graphics.drawable.Drawable
import android.widget.TextView
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.support.v7.widget.AppCompatButton
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ImageView
import com.astra.koksharov.astramultichosewithdatacalendar.R
import kotlinx.android.synthetic.main.cell_rl_button.view.*
import org.w3c.dom.Text
import java.util.*
import kotlin.collections.ArrayList


class AstraRLCellButton(context: Context, calendar: Calendar) : RelativeLayout(context) {

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

    var tvArray = ArrayList<TextView>()

    lateinit var calendar : Calendar

    init {

//        // if our context is not Activity we can't get View supplied by id
//        if (context !is Activity)
//            return

        // find relative layout by id
//        val v = (context as Activity).findViewById<View>(id)

        val layoutInflater = LayoutInflater.from(context)//context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = layoutInflater.inflate(R.layout.cell_rl_button, parent as (ViewGroup?))//parent as (ViewGroup?) R.id.cellRLRelativeLayout

//        // is it RelativeLayout ?
//        if (v !is RelativeLayout)
//            return

        //cast it to relative layout
//        val layout = v as RelativeLayout
        val layout = view as RelativeLayout

        // copy layout parameters
//        val params = layout.layoutParams
//        this.layoutParams = params

        // here I am using temporary instance of Button class
        // to get standard button background and to get button text color

        val bt = AppCompatButton(context)//AppCompatButton(context)
        this.setBackgroundDrawable(bt.getBackground())

        // copy all child from relative layout to this button
        while (layout.childCount > 0) {
            val vchild = layout.getChildAt(0)
            layout.removeViewAt(0)
            this.addView(vchild)

            // if child is textView set its color to standard buttong text colors
            // using temporary instance of Button class
            if (vchild is TextView) {
                vchild.setTextColor(bt.getTextColors())

                tvArray.add(vchild)
            }

            // just to be sure that child views can't be clicked and focused
            vchild.isClickable = false
            vchild.isFocusable = false
            vchild.isFocusableInTouchMode = false


        }

        // remove all view from layout (maybe it's not necessary)
//        layout.removeAllViews()

        // set that this button is clickable, focusable, ...
        this.isClickable = true
        this.isFocusable = true
        this.isFocusableInTouchMode = false

        // replace relative layout in parent with this one modified to looks like button
//        val vp = layout.parent as ViewGroup
//        val index = vp.indexOfChild(layout)
//        vp.removeView(layout)
//        vp.addView(this, index)

        this.id = id

        this.calendar = Calendar.getInstance()
        this.calendar.time = calendar.time
        setMarkText("0")
        setMainText(this.calendar.get(Calendar.DAY_OF_MONTH).toString())

        setBackgroundColor(backColor)
    }

    // method for setting texts for the text views
    fun setMainText(text: CharSequence) {
        val id: Int = R.id.cellRLMainTextView
        val v = findViewById<View>(id)
        if (null != v && v is TextView) {
            (v as TextView).text = text
        }

    }

    // method for setting texts for the text views
    fun setMarkText(text: CharSequence) {
        val id: Int = R.id.cellRLMarkTextView
        val v = findViewById<View>(id)
        if (null != v && v is TextView) {
            (v as TextView).text = text
        }

    }

    public fun buttonClicked(chosenState : Boolean): Unit {
        val id: Int = R.id.cellRLMarkTextView
        val v = findViewById<View>(id)
        if (null != v && v is TextView) {
            var tv = (v as TextView)
            tv.text = (Integer.parseInt(tv.text.toString()) + 1).toString();
        }

        chosen = chosenState

        var str = ""
        for (tv : TextView in tvArray)
            str += " ${tv.javaClass.canonicalName}:${tv.text} "
        Log.i("<CELL BUTTON>", " Color:${backColor}")

        requestLayout()
    }


//    // method for setting drawable for the images
//    fun setImageDrawable(id: Int, drawable: Drawable) {
//
//        val v = findViewById<View>(id)
//        if (null != v && v is ImageView) {
//            (v as ImageView).setImageDrawable(drawable)
//        }
//
//    }

//    // method for setting images by resource id
//    fun setImageResource(id: Int, image_resource_id: Int) {
//
//        val v = findViewById<View>(id)
//        if (null != v && v is ImageView) {
//            (v as ImageView).setImageResource(image_resource_id)
//        }
//
//    }

}