package ru.astralight.koksharov.organizer.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ListView
import com.astra.koksharov.astralightorganizer.R
import java.util.*
import android.widget.ArrayAdapter
import android.view.MenuItem
import android.widget.AdapterView.AdapterContextMenuInfo
import android.view.ContextMenu.ContextMenuInfo
import android.view.ContextMenu
import java.lang.Exception
import android.content.Intent
import android.content.ContentValues
import android.widget.ListAdapter
import com.astra.koksharov.organizerdb.*
import ru.astralight.koksharov.organizer.di.startRStream

class MainActivity : AppCompatActivity() {

    lateinit var left: Button
    lateinit var right: Button
    lateinit var datetime: Button
    lateinit var statistics: Button
    lateinit var add: Button

    lateinit var list: ListView
    var arrayList = ArrayList<String>();
    lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val calendar = Calendar.getInstance()
        var date: String =
            "${calendar.get(Calendar.YEAR)}.${ (calendar.get(Calendar.MONTH) + 1) }.${calendar.get(Calendar.DAY_OF_MONTH)}"

        //region buttons
        left = this.findViewById<Button>(R.id.main_left)
        right = this.findViewById<Button>(R.id.main_right)
        datetime = this.findViewById<Button>(R.id.main_datetime)
        statistics = this.findViewById<Button>(R.id.main_statistics)
        add = this.findViewById<Button>(R.id.main_add)

        datetime.setText(date)

        left.setOnClickListener {  buttonEvent(it) }
        right.setOnClickListener {  startRStream() } //buttonEvent(it)
        datetime.setOnClickListener {  buttonEvent(it) }
        statistics.setOnClickListener {  buttonEvent(it) }
        add.setOnClickListener {  buttonEvent(it) }
        //endregion

        //region listView

        //debug
        arrayList = arrayListOf()//"First element", "Second Element", "Third Element", "..."

        list = findViewById(R.id.main_listview)

        adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1, arrayList
        );
        list.adapter = adapter as ListAdapter?

        //endregion

        //Регистрация контекстного меню
        registerForContextMenu(list);
    }

    //region item context menu
    override public fun onCreateContextMenu(menu: ContextMenu, v: View, menuInfo: ContextMenuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo)

        val inflater = menuInflater
        inflater.inflate(R.menu.main_context_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info = item.getMenuInfo() as AdapterContextMenuInfo
        when (item.getItemId()) {
            R.id.main_item_in_heap -> {
                Log.d("astralight debug", "ITEM:${item.getItemId()}:${info.id}:${info.position}:${info.targetView.id}")
                try {
                    arrayList.removeAt(info.id.toInt())
                    adapter.notifyDataSetChanged()
                } catch (ex: Exception){
                    ex.printStackTrace()
                }
//                (ArrayAdapter)( list.adapter).main_add.main_add
                return true
            }
            R.id.main_item_Successed -> {
                Log.d("astralight debug", "ITEM:${item.getItemId()}:${info.id}:${info.position}:${info.targetView.id}")
                arrayList.add("ITEM SUC:${item.getItemId()}:${info.id}")
                adapter.notifyDataSetChanged()
                return true
            }
            R.id.main_item_transfer -> {
                Log.d("astralight debug", "ITEM:${item.getItemId()}:${info.id}:${info.position}:${info.targetView.id}")
                arrayList.add("ITEM TRA:${item.getItemId()}:${info.id}")
                adapter.notifyDataSetChanged()
                return true
            }
            else -> return super.onContextItemSelected(item)
        }
    }
    //endregion

    //region button event
    fun buttonEvent(ex: View){
        when(ex.id){
            R.id.main_left -> {
//                arrayList.add("ITEM LEF")
                arrayList.clear()

                val resolver = contentResolver
                val cursor = resolver.query(
                    PROVIDER_TASKS,
                    PROJECTION_TASKS,
                    null,
                    null,
                    null
                )
                if (cursor != null) {
                    cursor.moveToFirst()
                    cursor.moveToPrevious()
                    while (cursor.moveToNext()) {
                        var _id = cursor.getInt(cursor.getColumnIndex(FIELD_TASK_ID))
                        var type = cursor.getInt(cursor.getColumnIndex(FIELD_TASK_TYPE))
                        var name = cursor.getString(cursor.getColumnIndex(FIELD_TASK_NAME))
                        var dateBegin = cursor.getString(cursor.getColumnIndex(FIELD_TASK_DATE_BEGIN))
                        var dateEnd = cursor.getString(cursor.getColumnIndex(FIELD_TASK_DATE_END))
                        arrayList.add("${_id}:${name}:${type}:${dateBegin}:${dateEnd}")
                        Log.d("astralight debug", "${_id}:${name}:${type}:${dateBegin}:${dateEnd}")
                    }
                }

                adapter.notifyDataSetChanged()
                Log.d("astralight debug","MAIN LEFT PRESSED")
            }
            R.id.main_right -> {
//                arrayList.add("ITEM RIG")
                adapter.notifyDataSetChanged()
                Log.d("astralight debug","MAIN RIGHT PRESSED")
            }
            R.id.main_datetime -> {

                val resolver = contentResolver
                val values = ContentValues()

                val cursor = resolver.query(
                    PROVIDER_TASKS,
                    PROJECTION_TASKS,
                    null,
                    null,
                    null
                )

                if (cursor != null) {
                    val _id =  (Math.random() * 100).toInt()
                    val type = arrayList.size
                    val name = (Math.random() * 100).toString()
                    val dateBegin = ""
                    val dateEnd = ""

                    arrayList.add("${_id}:${name}:${type}:${dateBegin}:${dateEnd}")
                    Log.d("astralight debug", "${_id}:${name}:${type}:${dateBegin}:${dateEnd}")

                    values.put(FIELD_TASK_ID, _id);
                    values.put(FIELD_TASK_NAME, type);
                    values.put(FIELD_TASK_TYPE, name);
                    values.put(FIELD_TASK_DATE_BEGIN, dateBegin);
                    values.put(FIELD_TASK_DATE_END, dateEnd);
                    resolver.insert(PROVIDER_TASKS, values);
                    cursor.close();
                }

//                arrayList.add("ITEM DAT")
                adapter.notifyDataSetChanged()
                Log.d("astralight debug","MAIN DATE PRESSED")
            }
            R.id.main_statistics -> {
                val i = Intent(this, Statistics::class.java)
                startActivityForResult(i, 1)
                Log.d("astralight debug","MAIN STATS PRESSED")
            }
            R.id.main_add -> {
                val i = Intent(this, TaskAddActivity::class.java)
                startActivityForResult(i, 1)
//                arrayList.add("ITEM ADD")
//                adapter.notifyDataSetChanged()
                Log.d("astralight debug","MAIN ADD PRESSED")
            }

        }
    }
    //endregion

    public fun updateList(){

    }
}
