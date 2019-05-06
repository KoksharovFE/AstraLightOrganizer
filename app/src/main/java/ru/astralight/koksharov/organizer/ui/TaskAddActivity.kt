package ru.astralight.koksharov.organizer.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import com.astra.koksharov.astralightorganizer.R
import android.app.Activity
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import ru.astralight.koksharov.organizer.ui.taskadd.TsskAddAdapter
import java.lang.Exception


class TaskAddActivity : Activity(){//AppCompat

    lateinit var taskAddAdapter : TsskAddAdapter

    lateinit var left : Button
    lateinit var add : Button

    lateinit var name : EditText

    private lateinit var grid : GridView

    var lastTaskPositionPressed = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_add)

        left = findViewById(R.id.add_task_left)
        add = findViewById(R.id.add_task_add)
        name = findViewById(R.id.add_task_name)
        grid = findViewById(R.id.add_task_grid_view)

        left.setOnClickListener {  buttonEvent(it) }
        add.setOnClickListener {  buttonEvent(it) }

        name.setHint(R.string.add_hint)
//        name.setAutofillHints("task name")//target 22 < 26

//        val layout = TableLayout(this)
//        grid.layoutParams = TableLayout.LayoutParams(4, 5)
        grid.setNumColumns(4);
        grid.setPadding(1, 1, 1, 1)

        taskAddAdapter = TsskAddAdapter(this, layoutInflater, this)
        grid.adapter = taskAddAdapter

        //Регистрация контекстного меню
//        registerForContextMenu(grid);//todo grid context menu


        //layoutInflater
    }

    //region item options menu
    override fun onCreateOptionsMenu(menu : Menu): Boolean{
        val inflater = menuInflater
        inflater.inflate(R.menu.task_add_grid_menu, menu)
        return true
    }

    @Deprecated("Unused")//unused
    override fun onCreateContextMenu(menu: ContextMenu, v: View, menuInfo: ContextMenu.ContextMenuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo)

        val inflater = menuInflater
        inflater.inflate(R.menu.task_add_grid_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        val info = item.getMenuInfo() as AdapterView.AdapterContextMenuInfo
        when (item.getItemId()) {
            R.id.task_add_grid_ditribute -> {
                Log.d("astralight debug", "ITEM:${item.getItemId()}")
                try {
                    val i = Intent(this, TaskDistribution::class.java)
                    i.putExtra("name", (taskAddAdapter.getItem(lastTaskPositionPressed)).toString() );
                    this.startActivityForResult(i, 1)
                } catch (ex: Exception){
                    ex.printStackTrace()
                }
//                (ArrayAdapter)( list.adapter).main_add.main_add
                return true
            }
            R.id.task_add_grid_edit -> {
                Log.d("astralight debug", "ITEM:${item.getItemId()}")
                name.setText((taskAddAdapter.getItem(lastTaskPositionPressed)).toString())
                taskAddAdapter.removeItem(lastTaskPositionPressed)
                taskAddAdapter.notifyDataSetChanged()
                return true
            }
            R.id.task_add_grid_delete -> {
                Log.d("astralight debug", "ITEM:${item.getItemId()}")
                taskAddAdapter.removeItem(lastTaskPositionPressed)
                taskAddAdapter.notifyDataSetChanged()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    //endregion

    //region button event
    fun buttonEvent(ex: View){
        when(ex.id){
            R.id.add_task_left -> {
//                arrayList.add("ITEM LEF")
//                adapter.notifyDataSetChanged()

//                setResult(Activity.RESULT_CANCELED, returnIntent);//no return data
                //                returnIntent.putExtra("result", result)
                val returnIntent = Intent()
                setResult(Activity.RESULT_OK, returnIntent)
                finish()

                Log.d("astralight debug","ADD LEFT PRESSED")
            }
            R.id.add_task_add -> {
                if (!name.text.toString().equals("")) {
                    taskAddAdapter.addItem(name.text.toString())
                    taskAddAdapter.notifyDataSetChanged()
                    name.setText("")
                }

//                val i = Intent(this, TaskDistribution::class.java)
//                startActivityForResult(i, 1)
                Log.d("astralight debug","ADD ADD PRESSED")
            }

        }
    }

    fun gridButtonEvent(position: Int){
        lastTaskPositionPressed = position
        this.openOptionsMenu();
//        val i = activity.intent
//        val i = Intent(this, TaskDistribution::class.java)
//        i.putExtra("name", naming);
//        this.startActivityForResult(i, 1)
    }
    //endregion
}
