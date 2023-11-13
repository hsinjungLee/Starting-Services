package com.example.startingservices

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    private lateinit var editText: EditText
    private lateinit var startButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText = findViewById(R.id.editText)
        startButton = findViewById(R.id.Startbutton)

        startButton.setOnClickListener {
            val inputValue = editText.text.toString().toIntOrNull()
            if (inputValue != null) {
                val serviceIntent = Intent(this, CountdownService::class.java)
                serviceIntent.putExtra(CountdownService.EXTRA_TIME, inputValue)
                startService(serviceIntent)
            }
        }
    }
}
