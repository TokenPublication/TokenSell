package com.example.TokenSell

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class PreviousSalesActivity : AppCompatActivity() {

    private lateinit var textViewSales : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_previous_sales)

        textViewSales = findViewById(R.id.textViewSales)

        val sharedPref = getSharedPreferences("sharedPref", MODE_PRIVATE)
        val value: String? = sharedPref.getString("string", "")
        textViewSales.text = value
        //var sharedPref: SharedPreferences = Context.getSharedPreferences("sharedPref", Context.MODE_PRIVATE)
        //var savedString: String = sharedPref.getString("stringValue", "Kayıt Yok")

    }
}