package com.example.startingservices

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import kotlinx.coroutines.*

class CountdownService : Service() {
    companion object {
        const val EXTRA_TIME = "extra_time"
    }

    private lateinit var job: Job

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val time = intent?.getIntExtra(EXTRA_TIME, 0) ?: 0

        job = GlobalScope.launch(Dispatchers.Default) {
            for (i in time downTo 0) {
                Log.d("CountdownService", "Countdown: $i seconds remaining")
                delay(1000)
            }
            stopSelf()
        }

        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        if (::job.isInitialized && job.isActive) {
            job.cancel()
        }
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }
}
