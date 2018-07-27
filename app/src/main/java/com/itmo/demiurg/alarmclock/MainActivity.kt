package com.itmo.demiurg.alarmclock

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TimePicker
import android.widget.Toast
import android.widget.ToggleButton

import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var alarmTimePicker: TimePicker
    lateinit var alarmManager: AlarmManager
    var pendingIntent: PendingIntent? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        alarmTimePicker = findViewById(R.id.timePicker)
        alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager

        alarmTimePicker.setIs24HourView(true)
    }

    fun onToggleClicked(view: View) {
        if ((view as ToggleButton).isChecked) {
            Toast.makeText(applicationContext, "ALARM ON", Toast.LENGTH_SHORT).show()
            val calendar = Calendar.getInstance()
            calendar.set(Calendar.HOUR_OF_DAY, alarmTimePicker.hour)
            calendar.set(Calendar.MINUTE, alarmTimePicker.minute)
            val intent = Intent(this, AlarmReceiver::class.java)
            pendingIntent = PendingIntent.getBroadcast(applicationContext, 0, intent, 0)
            var time = calendar.timeInMillis
            if (System.currentTimeMillis() > time) {
                time += 1000 * 60 * 60 * 24
            }
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, time, 10000, pendingIntent)
        } else {
            alarmManager.cancel(pendingIntent)
            Toast.makeText(applicationContext, "ALARM OFF", Toast.LENGTH_SHORT).show()
        }
    }

    fun onStartRingClicked(view: View) {
        MyRingtoneManager.startRingtone(view.context)
    }

    fun onStopRingClicked(view: View) {
        MyRingtoneManager.stopRingtone()
    }

}
