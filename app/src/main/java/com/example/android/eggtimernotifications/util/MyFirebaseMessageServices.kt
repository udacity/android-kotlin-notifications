package com.example.android.eggtimernotifications.util

import android.app.NotificationManager
import android.util.Log
import androidx.core.content.ContextCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessageServices : FirebaseMessagingService() {
    override fun onNewToken(p0: String?) {

        Log.i("onNewToken", p0.toString())

    }

    override fun onMessageReceived(p0: RemoteMessage?) {
        Log.i("from", p0?.from.toString())
        p0?.data?.let {
            Log.d("dataEnt", p0.data.toString())
        }
        p0?.notification?.let {
            sendMessage(it.body)
        }
    }

    private fun sendMessage(body: String?) {
        val notificationCompat = ContextCompat
            .getSystemService(
                applicationContext,
                NotificationManager::class.java
            ) as NotificationManager
        notificationCompat.sendNotification(body.toString(), applicationContext)
    }
}