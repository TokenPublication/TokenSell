package com.example.TokenSell

import android.os.Bundle
import android.view.View
import android.view.WindowManager
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
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)

        val textViewPreviousSales = findViewById<TextView>(R.id.previousSalesNotifierTextView)
        val recyclerViewSales = findViewById<RecyclerView>(R.id.recyclerViewSales)

        //Bu aktivitede en ilk yapmamız gereken şey SharedPreferences'tan Sales ArrayList'i çekmemiz
        salesList = prefsConfig.readListFromPref(this)

        if(!salesList.isEmpty()) textViewPreviousSales.visibility = View.INVISIBLE

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