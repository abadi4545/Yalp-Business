package com.arkam.yelp.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.arkam.yelp.R
import android.content.Intent
import android.os.Handler
import com.arkam.yelp.util.Constant

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed({
            val intent = Intent(this@SplashActivity, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }, Constant.SPLASH_TIME.toLong())
    }
}