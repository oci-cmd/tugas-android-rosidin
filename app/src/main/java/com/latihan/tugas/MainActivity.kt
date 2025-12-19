package com.latihan.tugas

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.method.LinkMovementMethod // IMPORT TAMBAHAN
import android.text.util.Linkify           // IMPORT TAMBAHAN
import android.widget.Button
import android.widget.TextView             // IMPORT TAMBAHAN
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private var isKeepOn: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        splashScreen.setKeepOnScreenCondition { isKeepOn }

        Handler(Looper.getMainLooper()).postDelayed({
            isKeepOn = false
        }, 5000)

        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupButtons()
    }

    private fun setupButtons() {
        val dataModul = mapOf(
            R.id.button_fragment to "https://drive.google.com/file/d/17JcclGJNU9iex6KrY1ngvRzaiNGBOF4L/view?usp=sharing",
            R.id.button_list_view to "https://drive.google.com/file/d/10k4rLMy241q5PF5zLba3PCVEmBLuntDJ/view?usp=sharing",
            R.id.button_aritmatika to "https://drive.google.com/file/d/10k4rLMy241q5PF5zLba3PCVEmBLuntDJ/view?usp=sharing",
            R.id.button_shared_preferences to "https://drive.google.com/file/d/1Kl7R1j8rmnt3FDX9kGuS604g6EplY375/view?usp=sharing",
            R.id.button_sqlite_database to "https://drive.google.com/file/d/1DDV6kDhX_volL3vKwKblhiV9SVZTswD8/view?usp=sharing",
            R.id.button_rest_api to "https://drive.google.com/file/d/1C0TT-eoT4hg18jInx5iGmHk_ucwDtiII/view?usp=sharing",
            R.id.button_message to "https://drive.google.com/file/d/LINK_MESSAGE_DISINI",
            R.id.button_map_api to "https://drive.google.com/file/d/LINK_MAPS_DISINI",
            R.id.button_glide_retrofit to "https://drive.google.com/file/d/LINK_RETROFIT_DISINI",
            R.id.button_firebase to "https://drive.google.com/file/d/LINK_FIREBASE_DISINI",
            R.id.button_final to "https://drive.google.com/file/d/LINK_FINAL_DISINI"
        )

        for ((idTombol, linkDrive) in dataModul) {
            findViewById<Button>(idTombol).setOnClickListener {
                tampilkanDialogModul(linkDrive)
            }
        }
    }

    private fun tampilkanDialogModul(linkUrl: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Info Praktikum")

        // Teks link akan otomatis terdeteksi nanti
        builder.setMessage("Silahkan kerjakan sesuai Modul dari link:\n\n$linkUrl")

        builder.setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
        }

        // 1. Buat Dialog tapi jangan langsung "tampilkan" begitu saja
        val dialog = builder.create()
        dialog.show()

        // 2. KUNCI UTAMA: Mengambil komponen TextView pesan dari dalam dialog
        // Kita akses menggunakan ID bawaan android: android.R.id.message
        val messageView = dialog.findViewById<TextView>(android.R.id.message)

        // 3. Aktifkan Linkify agar teks URL berubah jadi Link Aktif
        if (messageView != null) {
            Linkify.addLinks(messageView, Linkify.WEB_URLS)
            messageView.movementMethod = LinkMovementMethod.getInstance()
        }
    }
}