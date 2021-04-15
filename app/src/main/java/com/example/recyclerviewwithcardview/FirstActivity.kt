package com.example.recyclerviewwithcardview

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FirstActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)

        val buttonProducts = findViewById<Button>(R.id.buttonProducts)
        val buttonQuickSell = findViewById<Button>(R.id.buttonQuickSell)
        val buttonExit = findViewById<Button>(R.id.buttonExit)

        buttonProducts.setOnClickListener{
            val intent = Intent(this@FirstActivity, MainActivity::class.java)
            startActivity(intent)
        }

        buttonQuickSell.setOnClickListener{
            val intent = Intent(this@FirstActivity, QuickSellActivity::class.java)
            startActivity(intent)
        }

        buttonExit.setOnClickListener{
            finishAndRemoveTask() //Finishes all activities in this task and removes it from the recent tasks list.
        }


    }
}