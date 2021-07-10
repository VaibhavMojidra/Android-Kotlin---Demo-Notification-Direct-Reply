package com.vaibhavmojidra.notificationdirectreplykotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.RemoteInput
import androidx.databinding.DataBindingUtil
import com.vaibhavmojidra.notificationdirectreplykotlin.databinding.ActivityMainBinding
import java.util.*


class MainActivity : AppCompatActivity() {
    private val CHANNEL_ID="com.vaibhavmojidra"
    private val CHANNEL_NAME="com.vaibhavmojidra"
    private val REPLY_KEY="REPLY_KEY"
    private lateinit var notificationManagerCompat: NotificationManagerCompat
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.showNotification.setOnClickListener {
            notificationManagerCompat= NotificationManagerCompat.from(this)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channel = NotificationChannel(
                    CHANNEL_ID,
                    CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_DEFAULT
                )
                notificationManagerCompat.createNotificationChannel(channel)
            }

            val tapResultIntent=Intent(this,SecondActivity::class.java)
            val pendingIntent:PendingIntent= PendingIntent.getActivity(this,0,tapResultIntent,PendingIntent.FLAG_CANCEL_CURRENT)
            //Reply Action
            val remoteInput:RemoteInput=RemoteInput.Builder(REPLY_KEY).run {
                setLabel("Insert your text here")
                build()
            }

            val replayAction=NotificationCompat.Action.Builder(
                0,
                "Reply",
                pendingIntent
            ).addRemoteInput(remoteInput).build()




            val notification: Notification =
                NotificationCompat.Builder(this@MainActivity, CHANNEL_ID)
                    .setSmallIcon(android.R.drawable.ic_menu_camera)
                    .setContentTitle("Notification Title")
                    .setContentText("Notification Content Text")
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .addAction(replayAction)
                    .setAutoCancel(true)
                    .build()
            notificationManagerCompat.notify(90, notification)
        }
    }
}