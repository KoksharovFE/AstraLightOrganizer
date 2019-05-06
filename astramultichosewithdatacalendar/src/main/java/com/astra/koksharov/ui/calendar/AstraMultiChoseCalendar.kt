package com.astra.koksharov.ui.calendar

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View
import android.widget.ListView
import android.widget.TableLayout
import com.astra.koksharov.astramultichosewithdatacalendar.AstraMultiChoseCell
import com.astra.koksharov.astramultichosewithdatacalendar.R

/**
 * TODO: document your custom view class.
 */
class AstraMultiChoseCalendar(context: Context?) : TableLayout(context) {

    private lateinit var listView : ListView

    private var _exampleString: String? = getResources().getString(R.string.app_name); // TODO: use a default from R.string...
    private var _exampleColor: Int = Color.RED // TODO: use a default from R.color...
    private var _exampleDimension: Float = 0f // TODO: use a default from R.dimen...

    init{

    }

}
