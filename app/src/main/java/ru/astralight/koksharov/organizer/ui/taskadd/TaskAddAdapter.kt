package ru.astralight.koksharov.organizer.ui.taskadd

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import com.astra.koksharov.astralightorganizer.R
import ru.astralight.koksharov.organizer.ui.TaskAddActivity


class TsskAddAdapter(val mContext: Context, val mLayoutInflater : LayoutInflater, val activity: TaskAddActivity) : BaseAdapter() {

    var list = ArrayList<String>()
//    lateinit var layoutInflater : LayoutInflater

    fun addItem(item : String){
        list.add(item)
    }

    fun removeItem(arg0: Int){
        list.removeAt(arg0)
    }

    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(arg0: Int): Any {
        return list[arg0]
    }

    override fun getItemId(arg0: Int): Long {
        return arg0.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var grid: View

        if (convertView == null) {
//            grid = View(mContext)
//            val inflater = getLayoutInflater()

            grid = mLayoutInflater.inflate(R.layout.grid_layout_task_add, parent, false)
        } else {
            grid = convertView
        }

        var button = grid.findViewById<Button>(R.id.grid_layout_button)

        button.setBackgroundColor(Color.WHITE)
//        b.text = name.text.toString()
//        button.textSize = 10.0f
        button.setTextColor(Color.rgb(100, 100, 200))
        button.setOnClickListener {activity.gridButtonEvent(position)}
        button.setText(list[position])
//        val imageView = grid.findViewById(R.id.image) as ImageView
//        imageView.setImageResource(mThumbIds[position])

        return grid
    }

}