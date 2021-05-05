package com.example.TokenSell

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class FirstActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)

        val buttonProducts = findViewById<Button>(R.id.buttonProducts)
        val buttonQuickSell = findViewById<Button>(R.id.buttonQuickSell)
        val buttonPreviousSales = findViewById<Button>(R.id.buttonPreviousSales)
        val buttonExit = findViewById<Button>(R.id.buttonExit)

        buttonProducts.setOnClickListener{
            val intent = Intent(this@FirstActivity, MainActivity::class.java)
            startActivity(intent)
        }

        buttonQuickSell.setOnClickListener{
            val intent = Intent(this@FirstActivity, QuickSellActivity::class.java)
            startActivity(intent)
        }

        buttonPreviousSales.setOnClickListener {
            val intent = Intent(this@FirstActivity, PreviousSalesActivity::class.java)
            startActivity(intent)
        }

        buttonExit.setOnClickListener{
            finishAndRemoveTask() //Finishes all activities in this task and removes it from the recent tasks list.
        }


    }
}