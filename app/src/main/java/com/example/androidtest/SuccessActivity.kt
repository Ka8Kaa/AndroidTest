package com.example.androidtest

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SuccessActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_success)

        val name = intent.getStringExtra("name") ?: "Korisnik"
        val textView = findViewById<TextView>(R.id.textViewSuccess)
        textView.text = "$name, uspješno ste došli do 10 koraka."


        val buttonSendSMS = findViewById<Button>(R.id.buttonSendSMS)
        buttonSendSMS.setOnClickListener {
            sendSms(name)
        }
    }

    private fun sendSms(name: String) {
        val phoneNumber = "+385919801992"
        val message = "$name, uspješno ste došli do 10 koraka."

        val smsIntent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse("smsto:$phoneNumber")
            putExtra("sms_body", message)
        }

        try {
            startActivity(smsIntent)
        } catch (e: Exception) {
            Toast.makeText(this, "Nema dostupne aplikacije za slanje SMS-a.", Toast.LENGTH_SHORT).show()
        }
    }
}
