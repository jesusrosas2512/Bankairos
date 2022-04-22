package com.jesusrosas.kairosds.bankairos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.fragment.NavHostFragment
import com.jesusrosas.kairosds.bankairos.data.model.UserProvider
import com.jesusrosas.kairosds.bankairos.ui.account.AccountFragmentDirections
import com.jesusrosas.kairosds.bankairos.ui.utils.toast
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity(), NavHost {

    private var startTimer = false
    private var isSessionClosed = false
    private var timeInSeconds = 0L
    private var mHandler: Handler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override val navController: NavController
        get() = (supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment).navController

    override fun onPause() {
        super.onPause()

        resetTime()
        startTime()

        if (UserProvider.user.expat.isNotEmpty())
            startTimer = true

    }

    override fun onResume() {
        super.onResume()
        if (isSessionClosed) {
            UserProvider.closeSession()
            isSessionClosed = false
            navController.navigate(AccountFragmentDirections.toBaseFormFragment())
            toast("La sesión expiró")
        }
        startTimer = false
    }

    private var chronometer: Runnable = object : Runnable{
        override fun run() {
            try {
                if (startTimer) {
                    timeInSeconds++
                    updateStopWatch(timeInSeconds)
                }

            } finally {
                mHandler!!.postDelayed(this, 1000L)
            }
        }
    }

    private fun updateStopWatch(timeInSeconds: Long) {
        val formattedTime = getFormattedStopWatch(timeInSeconds * 1000)
        Log.i("Timer", formattedTime)
    }

    private fun getFormattedStopWatch(ms: Long): String {
        var milliseconds = ms
        val minutes = TimeUnit.MILLISECONDS.toMinutes(milliseconds)
        milliseconds -= TimeUnit.MINUTES.toMillis(minutes)
        val seconds = TimeUnit.MILLISECONDS.toSeconds(milliseconds)

        if (seconds == 10L){
            startTimer = false
            isSessionClosed = true
        }

        return "${if (minutes < 10) "0" else ""}$minutes:" +
                "${if (seconds < 10) "0" else ""}$seconds"
    }

    private fun resetTime(){
        mHandler?.removeCallbacks(chronometer)
        timeInSeconds = 0

        // Log.i("Timer", getString(R.string.time_format))
    }

    private fun startTime(){
        mHandler = Handler(Looper.getMainLooper())
        chronometer.run()
    }

}