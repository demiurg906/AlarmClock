package com.itmo.demiurg.alarmclock

import android.content.Context
import android.media.Ringtone
import android.media.RingtoneManager

object MyRingtoneManager {
    var ringtone: Ringtone? = null

    fun startRingtone(context: Context) {
        val uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
                ?: RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        ringtone = RingtoneManager.getRingtone(context, uri)
        ringtone?.play()
    }

    fun stopRingtone() {
        ringtone?.stop()
    }
}



