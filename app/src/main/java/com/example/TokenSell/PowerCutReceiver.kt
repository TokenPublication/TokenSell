package com.example.TokenSell

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import java.text.SimpleDateFormat
import java.util.*

// To be able to use transforming methods on JSON file, we extended our Transformer interface
class PowerCutReceiver : BroadcastReceiver(), Transformer {

    private lateinit var salesList: ArrayList<Sale>
    val prefsConfig = PrefsConfig()

    override fun onReceive(context: Context?, intent: Intent) {
        if (intent.action != null && intent.action == "token.intent.ACTION_POWERCUT_RESULT") {

            //Here we don't check if salesList is null or not, since we check that inside the PrefConfig
            salesList = prefsConfig.readListFromPref(context)

            val message = intent.getStringExtra("paymentBody")

            val eachSale: Sale =
                Sale(
                    convertStatus(message),
                    SimpleDateFormat("dd.MM.yyyy HH:mm").format(Date()),
                    convertInvNo(message),
                    totalAmount(message),
                    getPaymentType(message)
                )

            salesList.add(0, eachSale) //Her yeni satışı listenin en başına ekliyoruz.
            prefsConfig.writeListInPref(context, salesList)
            println("powercut *****_ $message")
        }
    }

}