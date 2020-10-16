package com.udacoding.mahasiswa.ui.splashScreen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.AnticipateOvershootInterpolator
import androidx.appcompat.app.AppCompatActivity
import com.udacoding.mahasiswa.R
import com.udacoding.mahasiswa.helper.SessionManager
import com.udacoding.mahasiswa.ui.beranda.BerandaActivity
import com.udacoding.mahasiswa.ui.register.MainActivity
import kotlinx.android.synthetic.main.activity_splash_screen.*

class SplashScreen : AppCompatActivity() {
    private var animation: Animation? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val session = SessionManager(this)

        Handler().postDelayed(Runnable {

            if (session.login ?: true) {
                startActivity(Intent(this, BerandaActivity::class.java))
            } else {
                startActivity(Intent(this, MainActivity::class.java))
            }
        }, 2100)


        animation = AnimationUtils.loadAnimation(applicationContext, R.anim.animation_splash)
        animation?.setInterpolator(AnticipateOvershootInterpolator())
        animation?.setDuration(2000)
        img_splash.startAnimation(animation)
    }
}