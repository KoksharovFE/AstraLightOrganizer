package ru.astralight.koksharov.organizer.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.widget.TextView
import com.astra.koksharov.astralightorganizer.R
import com.astra.koksharov.ui.calendar.AstraMultiChoseCalendar
import java.lang.ref.WeakReference
import android.support.v4.content.res.ResourcesCompat


class Statistics : AppCompatActivity() {

    lateinit var info : TextView
    var h: Handler? = null
    lateinit var AMCC : AstraMultiChoseCalendar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statistics)

        info = findViewById<TextView>(R.id.statistics_textview)//

        AMCC = findViewById<AstraMultiChoseCalendar>(R.id.stats_mutishosecalendar)

        AMCC.exampleString = "FIREEE"
        AMCC.exampleColor = 0xFF00FF
        AMCC.exampleDimension = 300f

//        val b = BitmapFactory.decodeResource(resources, R.drawable.ic_launcher_background)
        val d = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_launcher_background, null);
//        BitmapDrawable(resources, bitmap)
//        AMCC._exampleDrawable = d

        h = MyHandler(this)

        Thread(Runnable {thread_renew()}).start()//runOnUiThread(Runnable() {}) }


    }

    internal class MyHandler(activity: Statistics) : Handler() {

        var wrActivity: WeakReference<Statistics>

        init {
            wrActivity = WeakReference<Statistics>(activity)
        }

        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            val activity = wrActivity.get()
            if (activity != null)
//                activity!!.someMethod()
                activity.info.text = "${activity.info.text}\n${msg.arg1}"
        }
    }

    public fun thread_renew(){
        var counter: Int = 0;
        //while (true){
        for(i in 0..12){
//            var infos = findViewById<TextView>(R.id.statistics_textview)
            counter++
            var mes = Message()
            mes.arg1 = counter
            h?.sendMessage(mes)

//            h?.sendEmptyMessage(counter);
//            infos.setText("${infos.text}\n${counter}")
            Thread.sleep(1000)
        }

    }
}
