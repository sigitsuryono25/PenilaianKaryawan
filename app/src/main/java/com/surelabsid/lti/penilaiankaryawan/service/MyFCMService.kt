package com.surelabsid.lti.penilaiankaryawan.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.google.gson.Gson
import com.surelabsid.lti.penilaiankaryawan.R
import com.surelabsid.lti.penilaiankaryawan.main.pengumuman.AllPengumumanActivity
import com.surelabsid.lti.penilaiankaryawan.response.ResponseFCM

class MyFCMService : FirebaseMessagingService() {

    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
    }

    override fun onMessageReceived(p0: RemoteMessage) {
        super.onMessageReceived(p0)
        Log.d(MyFCMService::class.java.simpleName, "onMessageReceived: $p0")
        p0.notification?.let {
            notificationHandler(it.title.toString())
        }

        p0.data.let {
            val gson = Gson()
            val toJSON = gson.toJson(it)
            val data = gson.fromJson(toJSON, ResponseFCM::class.java)
            notificationHandler(data.message.toString())
        }
    }

    fun notificationHandler(message: String) {
        val pengumumanIntent = Intent(this, AllPengumumanActivity::class.java)
        val pendingIntent =
            PendingIntent.getActivity(this, 0, pengumumanIntent, PendingIntent.FLAG_ONE_SHOT)
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notificationChannel = "kspps_bmt_bima_chanel"
        val notification = NotificationCompat.Builder(this, notificationChannel)
        notification.setContentText(message)
            .setContentTitle("Pengumuman")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setSmallIcon(R.drawable.logo)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)

        createNotificationChannel(notificationManager, notification)

        with(NotificationManagerCompat.from(this)) {
            notify(1, notification.build())
        }
    }

    private fun createNotificationChannel(
        notificationManager: NotificationManager,
        notification: NotificationCompat.Builder
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.app_name)
            val desc = getString(R.string.app_name)
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel =
                NotificationChannel(getString(R.string.app_name), name, importance).apply {
                    description = desc
                }
            notification.setChannelId(getString(R.string.app_name))
            notificationManager.createNotificationChannel(channel)
        }
    }
}