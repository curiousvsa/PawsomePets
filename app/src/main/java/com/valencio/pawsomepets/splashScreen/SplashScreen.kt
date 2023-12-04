package com.valencio.pawsomepets.splashScreen

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.valencio.pawsomepets.MainActivity
import com.valencio.pawsomepets.R

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        val timer = object : Thread() {
            override fun run() {
                try {
                    sleep(7000)
                } catch (e: Exception) {
                    e.printStackTrace()
                } finally {
                    val petDetailsIntent = Intent(this@SplashScreen, MainActivity::class.java)
                    startActivity(petDetailsIntent)
                }
            }
        }
        timer.start()
    }


}