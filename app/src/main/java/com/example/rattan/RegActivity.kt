package com.example.rattan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.rattan.databinding.ActivityRegBinding

class RegActivity : AppCompatActivity() {
    private lateinit var binding : ActivityRegBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textView4.setOnClickListener{
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}