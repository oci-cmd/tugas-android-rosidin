package com.latihan.tugas

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

class MainActivity : AppCompatActivity() {
    // Variabel untuk menahan durasi splash screen (Simulasi Loading)
    private var isKeepOn: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {

        // 1. INSTALASI SPLASH SCREEN
        // Wajib dipanggil SEBELUM super.onCreate dan setContentView
        val splashScreen = installSplashScreen()

        super.onCreate(savedInstanceState)

        // 2. LOGIKA MENAHAN SPLASH SCREEN
        // Splash akan tetap muncul selama kondisi bernilai 'true'
        splashScreen.setKeepOnScreenCondition {
            isKeepOn
        }
        // Simulasi proses loading data di background selama 2 detik
        // Dalam aplikasi nyata, ini bisa diganti dengan proses ambil data API/Database
        android.os.Handler(android.os.Looper.getMainLooper()).postDelayed({
            isKeepOn = false // Saat false, Splash hilang dan masuk ke layout utama
        }, 5000) // 5000 ms = 5 detik


        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}