package com.vaibhavmojidra.notificationdirectreplykotlin

import android.app.Notification
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.RemoteInput
import androidx.databinding.DataBindingUtil
import com.vaibhavmojidra.notificationdirectreplykotlin.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding
    private val CHANNEL_ID="com.vaibhavmojidra"
    private val CHANNEL_NAME="com.vaibhavmojidra"
    private val REPLY_KEY="REPLY_KEY"
    private lateinit var notificationManagerCompat: NotificationManagerCompat
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_second)
        receiveInput()
    }

    private fun receiveInput() {
        val remoteInput=RemoteInput.getResultsFromIntent(intent)
        if(remoteInput!=null){
            binding.resultTextview.text=remoteInput.getCharSequence(REPLY_KEY).toString()

            notificationManagerCompat= NotificationManagerCompat.from(this)
            val notification: Notification =NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(android.R.drawable.ic_menu_camera)
                .setContentTitle("Notification Title")
                .setContentText("Replied")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true)
                .build()
            notificationManagerCompat.notify(90, notification)
        }
    }
}