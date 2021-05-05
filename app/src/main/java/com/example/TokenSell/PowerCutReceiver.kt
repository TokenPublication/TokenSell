package com.example.TokenSell

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity


class PowerCutReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent) {
        if (intent.action != null && intent.action == "token.intent.ACTION_POWERCUT_RESULT") {

            val myIntent = Intent()
            myIntent.putExtra("paymentBody", intent.getStringExtra("paymentBody"))

            //Aşağıda bu intent üstündeki "paymentBody" bilgisini sharedPreferences'a aktarıp
            //Başka bir aktivite olan PreviousSalesActivity'de (GEÇMİŞ) son satışın bilgisi olarak çekiyorum.
            val message = myIntent!!.getStringExtra("paymentBody")
            val sharedPref = context!!.getSharedPreferences("sharedPref", AppCompatActivity.MODE_PRIVATE)
            val editor = sharedPref.edit()
            editor.putString("string", message)
            editor.commit()
        }
    }

}