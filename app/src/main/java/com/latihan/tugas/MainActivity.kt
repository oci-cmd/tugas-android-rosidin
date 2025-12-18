package com.latihan.tugas

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    // Variabel untuk menahan durasi splash screen
    private var isKeepOn: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {

        // 1. INSTALASI SPLASH SCREEN (Wajib baris pertama)
        val splashScreen = installSplashScreen()

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // 2. LOGIKA MENAHAN SPLASH SCREEN
        splashScreen.setKeepOnScreenCondition {
            isKeepOn // Selama true, splash screen akan terus muncul
        }

        // 3. Simulasi Loading (5 Detik)
        Handler(Looper.getMainLooper()).postDelayed({
            isKeepOn = false // Ubah jadi false agar masuk ke layout utama
        }, 5000)

        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}