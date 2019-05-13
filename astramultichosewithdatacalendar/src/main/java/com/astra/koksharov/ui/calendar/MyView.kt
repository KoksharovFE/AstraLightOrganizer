//package com.astra.koksharov.ui.calendar
//
//import android.content.Context
//import android.graphics.Canvas
//import android.graphics.Color
//import android.graphics.Paint
//import android.graphics.drawable.Drawable
//import android.text.TextPaint
//import android.util.AttributeSet
//import android.view.LayoutInflater
//import android.view.View
//import com.astra.koksharov.astramultichosewithdatacalendar.R
//
///**
// * TODO: document your custom view class.
// */
//class MyView : View {
//
//    private var exampleString: String? = null // TODO: use a default from R.string...
//    private var exampleColor: Int = Color.RED // TODO: use a default from R.color...
//    private var exampleDimension: Float = 0f // TODO: use a default from R.dimen...
//
//    private var textPaint: TextPaint? = null
//    private var textWidth: Float = 300f
//    private var textHeight: Float = 200f
//    lateinit var inflater : LayoutInflater;
//    /**
//     * The text to draw
//     */
//    var exampleString: String?
//        get() = exampleString
//        set(value) {
//            exampleString = value
//            invalidateTextPaintAndMeasurements()
//        }
//
//    /**
//     * The font color
//     */
//    var exampleColor: Int
//        get() = exampleColor
//        set(value) {
//            exampleColor = value
//            invalidateTextPaintAndMeasurements()
//        }
//
//    /**
//     * In the example view, this dimension is the font size.
//     */
//    var exampleDimension: Float
//        get() = exampleDimension
//        set(value) {
//            exampleDimension = value
//            invalidateTextPaintAndMeasurements()
//        }
//
//    /**
//     * In the example view, this drawable is drawn above the text.
//     */
//    var exampleDrawable: Drawable? = null
//
//    constructor(context: Context) : super(context) {
//        inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater;
//        init(null, 0)
//    }
//
//    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
//        inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater;
//        init(attrs, 0)
//    }
//
//    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
//        inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater;
//        init(attrs, defStyle)
//    }
//
//    private fun init(attrs: AttributeSet?, defStyle: Int) {
//
//        val view = inflater.inflate(R.layout.sample_my_view, null) as View
////        view.setContentView(R.layout.activity_statistics)
//
//        // Load attributes
//        val a = context.obtainStyledAttributes(
//            attrs, R.styleable.MyView, defStyle, 0
//        )
//
//        exampleString = a.getString(
//            R.styleable.MyView_exampleString
//        )
//        exampleColor = a.getColor(
//            R.styleable.MyView_exampleColor,
//            exampleColor
//        )
//        // Use getDimensionPixelSize or getDimensionPixelOffset when dealing with
//        // values that should fall on pixel boundaries.
//        exampleDimension = a.getDimension(
//            R.styleable.MyView_exampleDimension,
//            exampleDimension
//        )
//
//        if (a.hasValue(R.styleable.MyView_exampleDrawable)) {
//            exampleDrawable = a.getDrawable(
//                R.styleable.MyView_exampleDrawable
//            )
//            exampleDrawable?.callback = this
//        }
//
//        a.recycle()
//
//        // Set up a default TextPaint object
//        textPaint = TextPaint().apply {
//            flags = Paint.ANTI_ALIAS_FLAG
//            textAlign = Paint.Align.LEFT
//        }
//
//        // Update TextPaint and text measurements from attributes
//        invalidateTextPaintAndMeasurements()
//    }
//
//    private fun invalidateTextPaintAndMeasurements() {
//        textPaint?.let {
//            it.textSize = exampleDimension
//            it.color = exampleColor
//            textWidth = it.measureText(exampleString)
//            textHeight = it.fontMetrics.bottom
//        }
//    }
//
//    override fun onDraw(canvas: Canvas) {
//        super.onDraw(canvas)
//
//        // TODO: consider storing these as member variables to reduce
//        // allocations per draw cycle.
//        val paddingLeft = paddingLeft
//        val paddingTop = paddingTop
//        val paddingRight = paddingRight
//        val paddingBottom = paddingBottom
//
//        val contentWidth = width - paddingLeft - paddingRight
//        val contentHeight = height - paddingTop - paddingBottom
//
//        exampleString?.let {
//            // Draw the text.
//            canvas.drawText(
//                it,
//                paddingLeft + (contentWidth - textWidth) / 2,
//                paddingTop + (contentHeight + textHeight) / 2,
//                textPaint
//            )
//        }
//
//        // Draw the example drawable on top of the text.
//        exampleDrawable?.let {
//            it.setBounds(
//                paddingLeft, paddingTop,
//                paddingLeft + contentWidth, paddingTop + contentHeight
//            )
//            it.draw(canvas)
//        }
//    }
//}
