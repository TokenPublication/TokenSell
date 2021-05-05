package com.example.TokenSell

import android.app.Activity
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson


class MainActivity : AppCompatActivity() { //Extending from AppCombatActivity will ensure that all the components work correctly.

    private lateinit var json : String // Each time we press the sell button, we're going to fill this object w/ new values
    private lateinit var ourSpecificList : MutableList<ItemModel> //Burda tanımladık ki diğer fonksiyonlardan da ulaşabilelim

    //The below code is for integration with Payment Gateway
    val PAYMENT_ACTIVITY_RESULT_CODE = 0x1007 // can be anything
    val PAYMENT_PROCESSOR_PACKAGE_NAME = "com.tokeninc.sardis.paymentgateway"
    val PAYMENT_PROCESSOR_APP_NAME = ".MainActivity"
    val TAG_ORDER_BODY = "orderBody"
    var appName : String = PAYMENT_PROCESSOR_PACKAGE_NAME + PAYMENT_PROCESSOR_APP_NAME

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonFab = findViewById<com.google.android.material.floatingactionbutton.FloatingActionButton>(R.id.fab)
        val textViewTotalCheckout = findViewById<TextView>(R.id.textViewTotalCheckout)
        ourSpecificList = getModels() // So that we only deal with one specifi list. That is,
                                      // we can keep the quantity changes that we pass to JSON
        val gson = Gson() // creating a Gson object

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false) //yine bir instance oluşturuyorz.
        recyclerView.adapter = Adapter(ourSpecificList, {        //Adapter class'ının içindeki değere ulaşmak için CallBack function kullandık
            textViewTotalCheckout.setText(it.toString() + " ₺")
        })

    //Burada yazdığımız Adapter class'ından (aşağıdaki modelleri vererek) bir instance oluşturuyoruz.

        buttonFab.setOnClickListener {
            json = gson.toJson(itemizeToDesiredJSONFormat(ourSpecificList))
            println(json)
            val intent = Intent()
            val bundle = Bundle()

            bundle.putString(TAG_ORDER_BODY, json)
            intent.putExtras(bundle)
            intent.component = ComponentName(PAYMENT_PROCESSOR_PACKAGE_NAME, appName)
            startActivityForResult(intent, PAYMENT_ACTIVITY_RESULT_CODE)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) { //Dönen mesajı yakalamak için.
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == PAYMENT_ACTIVITY_RESULT_CODE && resultCode == Activity.RESULT_OK)
        {
            val message = data!!.getStringExtra("paymentBody")
            val sharedPref = getSharedPreferences("sharedPref", MODE_PRIVATE)
            val editor = sharedPref.edit()
            editor.putString("string", message)
            editor.commit()
            println(message)
        }
    }

    fun itemizeToDesiredJSONFormat(itemList: MutableList<ItemModel>) : FinalModel {
        //burayı forEach() kullanarak güzel bir şekilde yaz.
        val formattedModels : MutableList<MinJSONModel> = mutableListOf<MinJSONModel>()


        ourSpecificList.forEach {
            if(it.quantity.toInt() != 0) formattedModels.add(MinJSONModel(it.title, ((it.itemPrice) * 100).toInt(), ((it.taxPercent) * 100).toInt(), ((it.quantity) * 1000).toInt()))
        }

        return FinalModel(formattedModels)
    }



    fun getModels(): MutableList<ItemModel> {

        val models = mutableListOf(
                ItemModel(R.drawable.zeytinyagi_1l, "Zeytinyağı 1L", "Komili", itemPrice = 50.0),
                ItemModel(R.drawable.peynir_1kg, "Peynir 1KG", "Yörükoğlu", itemPrice = 42.5),
                ItemModel(R.drawable.zeytin_1kg, "Zeytin 1KG", "Kürkçüler", itemPrice = 33.7),
                ItemModel(R.drawable.pizza_2dilim, "Pizza Dilimi", "Dardanel", itemPrice = 16.2),
                ItemModel(R.drawable.kefir_450ml, "Kefir 450ml", "Altınkılıç", itemPrice = 5.99),
                ItemModel(R.drawable.patos_party, "Cips Parti Boy", "Patos", itemPrice = 5.0),
                ItemModel(R.drawable.dis_macunu, "Diş Macunu", "Eyüp Sabri Tuncer", itemPrice = 10.0)
        )
        return models
    }
}