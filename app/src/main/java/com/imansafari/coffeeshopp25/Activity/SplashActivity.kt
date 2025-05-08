package com.imansafari.coffeeshopp25.Activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.imansafari.coffeeshopp25.MainActivity
import com.imansafari.coffeeshopp25.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Menampilkan splash screen selama 3 detik sebelum masuk ke MainActivity
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish() // Menutup SplashActivity agar tidak bisa kembali ke layar ini.
        }, 3000)

        // Jika ingin pengguna menekan tombol untuk masuk ke MainActivity
        binding.main.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}
