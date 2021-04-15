package com.example.recyclerviewwithcardview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() { //Extending from AppCombatActivity will ensure that all the components work correctly.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textViewTotalCheckout = findViewById<TextView>(R.id.textViewTotalCheckout)


        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false) //yine bir instance oluşturuyorz.
        recyclerView.adapter = Adapter(getModels(),{        //Adapter class'ının içindeki değere ulaşmak için CallBack function kullandık
            textViewTotalCheckout.setText(it.toString() + " ₺")
        })

    //Burada yazdığımız Adapter class'ından (aşağıdaki modelleri vererek) bir instance oluşturuyoruz.

    }



    fun getModels(): MutableList<ItemModel> {

        val models = mutableListOf(
                ItemModel(R.drawable.zeytinyagi_1l, "Zeytinyağı 1L", "Komili", itemPrice = 50),
                ItemModel(R.drawable.peynir_1kg, "Peynir 1KG", "Yörükoğlu", itemPrice = 42),
                ItemModel(R.drawable.zeytin_1kg, "Zeytin 1KG", "Kürkçüler", itemPrice = 33),
                ItemModel(R.drawable.pizza_2dilim, "Pizza Dilimi", "Dardanel", itemPrice = 16),
                ItemModel(R.drawable.kefir_450ml, "Kefir 450ml", "Altınkılıç", itemPrice = 5),
                ItemModel(R.drawable.patos_party, "Cips Parti Boy", "Patos", itemPrice = 5),
                ItemModel(R.drawable.dis_macunu, "Diş Macunu", "Eyüp Sabri Tuncer", itemPrice = 10)
        )
        return models
    }
}