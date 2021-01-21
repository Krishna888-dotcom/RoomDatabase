package com.example.dataactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import com.bumptech.glide.Glide
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Suppress("DEPRECATION")
class splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
//        Handler().postDelayed({
//            val intent = Intent(this, login::class.java)
//            startActivity(intent)
//            finish()
//        }, 3000)
        CoroutineScope(Dispatchers.IO).launch {
            delay(1000)
            startActivity(
                    Intent(this@splash,login::class.java)
            )
            finish()
        }
    }

}


