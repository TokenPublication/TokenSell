package com.example.TokenSell

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class PreviousSalesActivity : AppCompatActivity() {

    private lateinit var textViewSales: TextView
    private lateinit var salesList: ArrayList<Sale>
    val prefsConfig = PrefsConfig()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_previous_sales)

        //Bu aktivitede en ilk yapmamız gereken şey SharedPreferences'tan Sales ArrayList'i çekmemiz
        salesList = prefsConfig.readListFromPref(this)

        val recyclerViewSales = findViewById<RecyclerView>(R.id.recyclerViewSales)
        recyclerViewSales.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        ) //yine bir instance oluşturuyorz.
        recyclerViewSales.adapter = SaleAdapter(
            salesList
        )

    }
}