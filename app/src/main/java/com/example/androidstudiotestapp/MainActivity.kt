package com.example.androidstudiotestapp

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.pow
import kotlin.math.sqrt
import android.graphics.Color
import java.math.BigDecimal
import java.math.RoundingMode


class MainActivity : AppCompatActivity() {
    private var stack: MutableList<Double> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setTitle("RPN Calculator for Android")
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)

        val numericButtons = arrayOf(but0, but1, but2, but3, but4, but5, but6, but7, but8, but9)
        val colors = arrayOf(Color.BLACK, Color.BLUE, Color.RED, Color.GREEN, Color.YELLOW)

        var result = "0"
        var float = false
        var precision = 3
        var color = 0

        stack0.text = result

        fun fixString(value: Double): String {
            return BigDecimal(value).setScale(precision, RoundingMode.HALF_EVEN).toPlainString()
        }

        fun refreshStack() {
            val stackTextViews = arrayOf(stack0, stack1, stack2, stack3)
            stack0.text = result
            stackCount.text = "STACK: " + stack.size.toString()

            for (i in 1..3) {
                if (stack.size > i-1) stackTextViews[i].text = fixString(stack[stack.size-i])
                else stackTextViews[i].text = ""
            }
        }

        numericButtons[0].setOnClickListener() {
            if (result != "0") {
                result = joinStrings(result, "0")
                stack0.text = result
            }
        }

        for (i in 1..9) {
            numericButtons[i].setOnClickListener() {
                if (result == "0") result = ""
                result = joinStrings(result, i.toString())
                stack0.text = result
            }
        }

        butDot.setOnClickListener() {
            if (!float) {
                result = joinStrings(result, ".")
                stack0.text = result
                float = true
            }
        }

        butBack.setOnClickListener() {
            if (result.takeLast(1) == ".") float = false
            result = result.dropLast(1)
            if (result.isEmpty()) result = "0"
            stack0.text = result
        }

        butEnter.setOnClickListener() {
            stack += result.toDouble().round(precision)
            result = "0"
            refreshStack()
            float = false
        }

        butPlus.setOnClickListener() {
            if (stack.isNotEmpty()) {
                result = fixString(result.toDouble() + stack.takeLast(1)[0])
                stack.removeLast()
                refreshStack()
                float = true
            }
        }

        butMinus.setOnClickListener() {
            if (stack.isNotEmpty()) {
                result = fixString(result.toDouble() - stack.takeLast(1)[0])
                stack.removeLast()
                refreshStack()
                float = true
            }
        }

        butMultip.setOnClickListener() {
            if (stack.isNotEmpty()) {
                result = fixString(result.toDouble() * stack.takeLast(1)[0])
                stack.removeLast()
                refreshStack()
                float = true
            }
        }

        butDiv.setOnClickListener() {
            if (stack.isNotEmpty() && stack.takeLast(1)[0] != 0.0) {
                result = fixString(result.toDouble() / stack.takeLast(1)[0])
                stack.removeLast()
                refreshStack()
                float = true
            }
        }

        butRoot.setOnClickListener() {
            result = sqrt(result.toDouble()).round(precision).toString()
            refreshStack()
            float = true
        }

        butPower.setOnClickListener() {
            if (stack.isNotEmpty()) {
                result = fixString(result.toDouble().pow(stack.takeLast(1)[0]))
                stack.removeLast()
                refreshStack()
                float = true
            }
        }

        butDrop.setOnClickListener() {
            if (stack.isNotEmpty()) {
                stack.removeAt(0)
                refreshStack()
            }
        }

        butSwap.setOnClickListener() {
            if (stack.isNotEmpty()) {
                val temp = stack.last()
                stack[stack.size-1] = result.toDouble()
                result = fixString(temp)
                refreshStack()
                float = true
            }
        }

        butColor.setOnClickListener() {
            color = (color + 1) % 5
            mainView.setBackgroundColor(colors[color])
        }

        butPrec.setOnClickListener() {
            precision = (precision + 1) % 10
            precValue.text = "PRECISION: " + precision.toString()
        }
    }

    private fun joinStrings(str1: String, str2: String): String {
        return str1.plus(str2)
    }

    private fun Double.round(decimals: Int = 2): Double = "%.${decimals}f".format(this).toDouble()
}