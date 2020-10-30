package com.example.stars.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.stars.R
import com.example.stars.ui.main.MainActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        setHandler()
    }


    fun setHandler() {
        Handler().postDelayed({
            /* Create an Intent that will start the Menu-Activity. */

            var intent = Intent(
                this,
                MainActivity::class.java
            )
            startActivity(intent)
            finish()

        }, 1000)
    }
}
