package com.example.recycler_aston

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.recycler_aston.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.adapter = CountryAdapter()
    }
}