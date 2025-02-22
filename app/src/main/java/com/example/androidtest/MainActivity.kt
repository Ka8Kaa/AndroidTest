package com.example.androidtest

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private var counter = 0
    private lateinit var textViewCounter: TextView
    private lateinit var buttonUp: Button
    private lateinit var buttonDown: Button
    private lateinit var editTextName: EditText

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        textViewCounter = findViewById(R.id.textViewCounter)
        buttonUp = findViewById(R.id.buttonUp)
        buttonDown = findViewById(R.id.buttonDown)
        editTextName = findViewById(R.id.plainTextName)

        sharedPreferences = getPreferences(MODE_PRIVATE)
        counter = sharedPreferences.getInt("counter", 0)
        updateCounter()

        buttonUp.setOnClickListener {
            counter++
            updateCounter()
            saveCounter()
            checkCounter()
        }

        buttonDown.setOnClickListener {
            if (counter > 0) {
                counter--
            }
            updateCounter()
            saveCounter()
        }
    }

    private fun updateCounter() {
        textViewCounter.text = counter.toString()
    }

    private fun saveCounter() {
        val editor = sharedPreferences.edit()
        editor.putInt("counter", counter)
        editor.apply()
    }

    private fun checkCounter() {
        if (counter == 10) {
            val name = editTextName.text.toString()
            if (name.isNotEmpty()) {
                val intent = Intent(this, SuccessActivity::class.java).apply {
                    putExtra("name", name)
                }
                startActivity(intent)
                counter = 0
                updateCounter()
                saveCounter()
            } else {
                Toast.makeText(this, "Please enter your name before proceeding", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Toast.makeText(applicationContext, "onStart", Toast.LENGTH_SHORT).show()
        Log.i("MyLog", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Toast.makeText(applicationContext, "onResume", Toast.LENGTH_SHORT).show()
        Log.i("MyLog", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Toast.makeText(applicationContext, "onPause", Toast.LENGTH_SHORT).show()
        Log.i("MyLog", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Toast.makeText(applicationContext, "onStop", Toast.LENGTH_SHORT).show()
        Log.i("MyLog", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(applicationContext, "onDestroy", Toast.LENGTH_SHORT).show()
        Log.i("MyLog", "onDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        Toast.makeText(applicationContext, "onRestart", Toast.LENGTH_SHORT).show()
        Log.i("MyLog", "onRestart")
    }
}