package com.example.financialtracker.views

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.financialtracker.R
import com.example.financialtracker.views.fragment.HomeFragment

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)
        window.statusBarColor = ContextCompat.getColor(this, R.color.blue_dark)

        // Pindah ke halaman kedua setelah 5 detik
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000) // 3000 milidetik = 3 detik
    }
}