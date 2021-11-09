package com.example.TokenSell

import android.app.Activity
import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class MainActivity :
    AppCompatActivity(), Transformer { //Extending from AppCombatActivity will ensure that all the components work correctly.


    private lateinit var json: String // Each time we press the sell button, we're going to fill this object w/ new values
    private lateinit var ourSpecificList: MutableList<ItemModel> //Burda tanımladık ki diğer fonksiyonlardan da ulaşabilelim
    private lateinit var textViewTotalCheckout: TextView
    private lateinit var recyclerView: RecyclerView


    //The below code is for integration with Payment Gateway
    val PAYMENT_ACTIVITY_RESULT_CODE = 0x1007 // can be anything
    val PAYMENT_PROCESSOR_PACKAGE_NAME = "com.tokeninc.sardis.paymentgateway"
    val PAYMENT_PROCESSOR_APP_NAME = ".MainActivity"
    val TAG_ORDER_BODY = "orderBody"
    var appName: String = PAYMENT_PROCESSOR_PACKAGE_NAME + PAYMENT_PROCESSOR_APP_NAME

    private lateinit var salesList: ArrayList<Sale>
    val prefsConfig = PrefsConfig()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        textViewTotalCheckout = findViewById<TextView>(R.id.textViewTotalCheckout)
        //This is the list where we keep our previous sales
        salesList = prefsConfig.readListFromPref(this)

        val buttonFab =
            findViewById<com.google.android.material.floatingactionbutton.FloatingActionButton>(R.id.fab)
        ourSpecificList = getModels() // So that we only deal with one specific list. That is,
        // we can keep the quantity changes that we pass to JSON
        val gson = Gson() // creating a Gson object

        recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        ) //yine bir instance oluşturuyorz.
        recyclerView.adapter = Adapter(
            ourSpecificList,
            {        //Adapter class'ının içindeki değere ulaşmak için CallBack function kullandık
                textViewTotalCheckout.text = (it.toString() + " ₺")
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

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) { //Dönen mesajı yakalamak için.
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PAYMENT_ACTIVITY_RESULT_CODE && resultCode == Activity.RESULT_OK) {
            val message = data!!.getStringExtra("paymentBody")
            val sharedPref = getSharedPreferences("sharedPref", MODE_PRIVATE)

            val eachSale: Sale =
                Sale(
                    convertStatus(message),
                    SimpleDateFormat("dd.MM.yyyy HH:mm").format(Date()),
                    convertInvNo(message),
                    totalAmount(message),
                    getPaymentType(message)
                )


            salesList.add(0, eachSale) //Her yeni satışı listenin en başına ekliyoruz.
            prefsConfig.writeListInPref(this, salesList)
            println("normal *****_ $message")
            //Satıştan sonra ana ekrana atmak için
            val intent = Intent(this@MainActivity, FirstActivity::class.java)
            startActivity(intent)


        }
    }

    fun itemizeToDesiredJSONFormat(itemList: MutableList<ItemModel>): FinalModel {
        val totalCheckout = textViewTotalCheckout.text.toString().toDouble()
        val formattedModels: MutableList<MinJSONModel> = mutableListOf<MinJSONModel>()
        val onlyCustomerInfo: CustomerInfo = CustomerInfo("99999999999", "George Ivan", "1st District", "Bucureşti", "România")

        ourSpecificList.forEach {
            if (it.quantity.toInt() != 0) formattedModels.add(
                MinJSONModel(
                    ((it.itemPrice) * 100).toInt()
                )
            )
        }

        if(totalCheckout > 500) return FinalModel(formattedModels)
        else return FinalModel(formattedModels)
    }


    fun getModels(): MutableList<ItemModel> {

        val models = mutableListOf(
            ItemModel(R.drawable.zeytinyagi_1l, getString(R.string.olive_oil), "Komili", itemPrice = 50.0),
            ItemModel(R.drawable.peynir_1kg, getString(R.string.cheese), "Yörükoğlu", itemPrice = 42.5),
            ItemModel(R.drawable.zeytin_1kg, getString(R.string.olive), "Kürkçüler", itemPrice = 33.7),
            ItemModel(R.drawable.pizza_2dilim, getString(R.string.pizza), "Dardanel", itemPrice = 16.2),
            ItemModel(R.drawable.kefir_450ml, getString(R.string.kefir), "Altınkılıç", itemPrice = 5.99),
            ItemModel(R.drawable.patos_party, getString(R.string.chips), "Patos", itemPrice = 5.0),
            ItemModel(R.drawable.dis_macunu, getString(R.string.toothpaste), "Eyüp Sabri Tuncer", itemPrice = 10.0)
        )
        return models
    }

}