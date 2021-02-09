package com.example.cogbootcamp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cogbootcamp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView( binding.root)

        setButtonOnClickListeners()
    }

    private fun setButtonOnClickListeners() {
        binding.buttonReflexButtonGame.setOnClickListener {
            val intent =  Intent(this@MainActivity, ReflexButtonActivity::class.java)
            startActivity(intent)
        }

        binding.buttonSetting.setOnClickListener {
            // TODO
        }
    }
}