/*
 * Copyright (C) 2019 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.eggtimernotifications.util

import android.annotation.SuppressLint
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationCompat.GROUP_ALERT_ALL
import androidx.core.app.NotificationCompat.PRIORITY_HIGH
import com.example.android.eggtimernotifications.MainActivity
import com.example.android.eggtimernotifications.R
import com.example.android.eggtimernotifications.receiver.SnoozeReceiver

// Notification ID.
private val NOTIFICATION_ID = 0
private val REQUEST_CODE = 0
private val FLAGES = 0

/**
 * Builds and delivers the notification.
 *
 * @param context, activity context.
 */
@SuppressLint("WrongConstant")
fun NotificationManager.sendNotification(messageBody: String, applicationContext: Context) {
    // Create the content intent for the notification, which launches
    // this activity
    val contentIntent = Intent(applicationContext, MainActivity::class.java)
    val contentPendintItnent = PendingIntent.getActivity(
        applicationContext, NOTIFICATION_ID, contentIntent, PendingIntent.FLAG_UPDATE_CURRENT
    )

    val eggImg = BitmapFactory.decodeResource(
        applicationContext.resources, R.drawable.cooked_egg
    )
    val bigStyle = NotificationCompat.BigPictureStyle()
        .bigPicture(eggImg).bigLargeIcon(null)
    val snoozeIntent = Intent(applicationContext, SnoozeReceiver::class.java)
    val snoozeReceiverPending: PendingIntent = PendingIntent.getBroadcast(
        applicationContext, REQUEST_CODE, snoozeIntent, FLAGES
    )

    val notificationCompat = NotificationCompat.Builder(
        applicationContext, applicationContext.getString(R.string.egg_notification_channel_id)
    )
        .setGroupAlertBehavior(GROUP_ALERT_ALL)
        .setSmallIcon(R.drawable.egg_icon)
        .setContentTitle(applicationContext.getString(R.string.notification_title))
        .setContentText(messageBody)
        .setContentIntent(contentPendintItnent)
        .setAutoCancel(true)
        .setStyle(bigStyle)
        .setLargeIcon(eggImg)
        .addAction(
            R.drawable.egg_icon,
            applicationContext.getString(R.string.snooze),
            snoozeReceiverPending
        )
        .setPriority(PRIORITY_HIGH)

    // Build the notification

    // TODO: Step 1.8 use the new 'breakfast' notification channel

    // TODO: Step 1.3 set title, text and icon to builder

    // TODO: Step 1.13 set content intent

    // TODO: Step 2.1 add style to builder

    // TODO: Step 2.3 add snooze action

    // TODO: Step 2.5 set priority

    // TODO: Step 1.4 call notify

    notify(
        NOTIFICATION_ID, notificationCompat.build()
    )
}

fun NotificationManager.cancelNotification() {
    cancelAll()
}