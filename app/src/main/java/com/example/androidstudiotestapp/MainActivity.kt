package com.example.androidstudiotestapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val statusText: TextView = findViewById(R.id.output)
        val numericButtons = arrayOf(but0, but1, but2, but3, but4, but5, but6, but7, but8, but9)

        var result = "0"
        statusText.text = result
        var dot = false

        numericButtons[0].setOnClickListener() {
            if (result != "0") {
                result = joinStrings(result, "0")
                statusText.text = result
            }
        }

        for (i in 1..9) {
            numericButtons[i].setOnClickListener() {
                if (result == "0") result = ""
                result = joinStrings(result, i.toString())
                statusText.text = result
            }
        }

        butDot.setOnClickListener() {
            if (!dot) {
                result = joinStrings(result, ".")
                statusText.text = result
                dot = true
            }
        }

        butBack.setOnClickListener() {
            var lastChar = result.takeLast(1)
            result = result.dropLast(1)
            if (result.isEmpty()) result = "0"
            if (lastChar == ".") dot = false
            statusText.text = result
        }
    }

    private fun joinStrings(str1: String, str2: String): String {
        return str1.plus(str2)
    }
}