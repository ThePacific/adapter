package com.pacific.app.adapter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pacific.app.adapter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMainBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}
