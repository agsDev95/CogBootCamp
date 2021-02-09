package com.example.cogbootcamp.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.cogbootcamp.databinding.ActivityReflexButtonBinding
import com.example.cogbootcamp.dialog.ReflexResultDialog

class ReflexButtonActivity : AppCompatActivity()  {
    private lateinit var binding: ActivityReflexButtonBinding
    private var currentClickCount: Int = 0
    private var clickTimeStamps: ArrayList<Long>  = ArrayList()
    private var totalResponseTime: Float = 0.0f
    private var averageResponseTime: Float = 0.0f

    companion object {
        private const val MAX_COUNT: Int = 30
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReflexButtonBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setButtonOnClickListeners()
    }

    private fun setButtonOnClickListeners() {
        binding.startButton.setOnClickListener {
            startReflexTest()
        }

        binding.reflexButton.setOnClickListener { /* DO NOTHING */ }
        binding.reflexButton.isEnabled = false
    }

    private fun startReflexTest() {
        binding.reflexButton.setOnClickListener {
            recordClick()
        }
        binding.reflexButton.isEnabled = true
    }

    private fun recordClick()  {
        clickTimeStamps.add(System.currentTimeMillis())
        currentClickCount++

        if (currentClickCount == MAX_COUNT ) {
            binding.reflexButton.isEnabled = false
            generateReflexMetrics()
            showResultsDialog()
        }
    }

    private fun generateReflexMetrics() {
        totalResponseTime = formatResponseTime(clickTimeStamps[29] - clickTimeStamps[0])
        averageResponseTime = totalResponseTime / MAX_COUNT

        Log.e("RESULT", "totalResponseTime = $totalResponseTime")
        Log.e("RESULT", "averageResponseTime = $averageResponseTime")
    }

    private fun formatResponseTime(responseTime: Long): Float {
       return (responseTime / 1000.0f)
    }

    private fun showResultsDialog() {
        val fragmentManager = supportFragmentManager
        ReflexResultDialog(totalResponseTime).show(fragmentManager, "ReflexResultDialog")
    }
}