package com.example.fastfoodorderapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fastfoodorderapplication.R
import com.example.fastfoodorderapplication.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}