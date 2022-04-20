package com.example.androidstudiotestapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.androidstudiotestapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        var result = 0

        binding.but0.setOnClickListener() {
            result *= 10
            setStatusText(result.toString())
        }
        binding.but1.setOnClickListener() {
            result = result * 10 + 1
            setStatusText(result.toString())
        }
        binding.but2.setOnClickListener() {
            result = result * 10 + 2
            setStatusText(result.toString())
        }
    }

    private fun setStatusText(str: String) {
        binding.statusText.text = str
    }

    private fun getStatusText(): String {
        return binding.statusText.text.toString()
    }

    private fun joinStrings(str1: String, str2: String): String {
        return str1.plus(str2)
    }
}