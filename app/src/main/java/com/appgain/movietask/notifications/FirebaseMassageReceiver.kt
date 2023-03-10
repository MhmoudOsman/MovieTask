package com.appgain.movietask.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import androidx.navigation.NavDeepLinkBuilder
import com.appgain.movietask.R
import com.appgain.movietask.ui.MainActivity
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

const val channel_id = "notification_channel"
const val channel_name = "com.appgain.movietask"


class FirebaseMassageReceiver : FirebaseMessagingService() {


    override fun onMessageReceived(remoteMessage: RemoteMessage) {

        if (remoteMessage.notification != null) {

            showNotification(
                remoteMessage.notification!!.title,
                remoteMessage.notification!!.body,
                remoteMessage.data
            )
        }
    }
    private fun getCustomDesign(title: String, message: String): RemoteViews {
        val remoteViews = RemoteViews(
            applicationContext.packageName,
            R.layout.notification
        )
        remoteViews.setTextViewText(R.id.title, title)
        remoteViews.setTextViewText(R.id.message, message)
        remoteViews.setImageViewResource(
            R.id.icon,
            R.drawable.logo
        )
        return remoteViews
    }

    // Method to display the notifications
    fun showNotification(title: String?, message: String?, data: MutableMap<String, String>) {

        val link = data["uri"]

//        val intent = Intent(this, MainActivity::class.java)
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
//
//        val pendingIntent = PendingIntent.getActivity(
//            this, 0, intent,
//            PendingIntent.FLAG_ONE_SHOT
//        )

        val pendingIntent = NavDeepLinkBuilder(applicationContext)
            .setGraph(R.navigation.nav_graph)
            .setComponentName(MainActivity::class.java)
            .setDestination(link!!)
            .createPendingIntent()

        var builder: NotificationCompat.Builder = NotificationCompat.Builder(
            applicationContext,
            channel_id
        )
            .setSmallIcon(R.drawable.logo)
            .setAutoCancel(true)
            .setVibrate(longArrayOf(1000, 1000, 1000, 1000, 1000))
            .setOnlyAlertOnce(true)
            .setContentIntent(pendingIntent)

        builder = builder.setContent(getCustomDesign(title!!,message!!))


        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager?
        // Check if the Android Version is greater than Oreo
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel =
                NotificationChannel(channel_id, channel_name, NotificationManager.IMPORTANCE_HIGH)
            notificationManager!!.createNotificationChannel(notificationChannel)
        }
        notificationManager!!.notify(0, builder.build())
    }

}