package com.somya.foodapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.somya.foodapp.R
import kotlinx.android.synthetic.main.splash_screen_layout.*

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen_layout)
        txtFoodAppName.alpha=0f
        imgFoodAppLogo.alpha=0f
        txtFoodAppName.animate().setDuration(2000).alpha(1f).withEndAction{
            val startAct= Intent(this, MainActivity::class.java)
            startActivity(startAct)
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
            finish()

        }
        imgFoodAppLogo.animate().setDuration(2000).alpha(1f).withEndAction{
            val startAct= Intent(this, MainActivity::class.java)
            startActivity(startAct)
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
            finish()

        }
    }
}