package com.example.TokenSell

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Bundle
import android.view.WindowManager
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import java.util.*


class FirstActivity : AppCompatActivity() {

    private lateinit var autoCompleteTextView: AutoCompleteTextView
    private lateinit var languages: Array<String>
    private lateinit var arrayAdapter: ArrayAdapter<String>


    fun arrangeLocate(Lang: String) {
        val locale = Locale(Lang)

        Locale.setDefault(locale)

        val config = Configuration()

        config.locale = locale
        baseContext.resources.updateConfiguration(config, baseContext.resources.displayMetrics)
    }


    fun setLocate(Lang: String) {

        val locale = Locale(Lang)

        Locale.setDefault(locale)

        val config = Configuration()

        config.locale = locale
        baseContext.resources.updateConfiguration(config, baseContext.resources.displayMetrics)

        val editor = getSharedPreferences("Settings", Context.MODE_PRIVATE).edit()
        editor.putString("My_Lang", Lang)
        editor.apply()

        val i = baseContext.packageManager.getLaunchIntentForPackage(baseContext.packageName)
        i!!.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        i!!.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

        finish()
        startActivity(i)
    }

    override fun onResume() {
        super.onResume()
        languages = resources.getStringArray(R.array.languages)
        arrayAdapter = ArrayAdapter(this, R.layout.dropdown_item, languages)
        autoCompleteTextView.setAdapter(arrayAdapter)
        println(".....asdaijcşoanca.scaşcşosmvimdfşvmdşfnv")
        autoCompleteTextView.setOnItemClickListener(OnItemClickListener { parent, view, position, id ->
            val selectedLang = autoCompleteTextView.text.toString()

            when (selectedLang) {
                "EN" -> {
                    setLocate("en")
                    println(selectedLang)
                    val sharedPreferences = getSharedPreferences("Settings", Activity.MODE_PRIVATE)
                    val language = sharedPreferences.getString("My_Lang", "")
                    println("Local: " + language)
                }
                "RO" -> {
                    setLocate("ro")
                    println(selectedLang)
                    val sharedPreferences = getSharedPreferences("Settings", Activity.MODE_PRIVATE)
                    val language = sharedPreferences.getString("My_Lang", "")
                    println("Local: " + language)
                }
                "TR" -> {
                    setLocate("tr")
                    println(selectedLang)
                    val sharedPreferences = getSharedPreferences("Settings", Activity.MODE_PRIVATE)
                    val language = sharedPreferences.getString("My_Lang", "")
                    println("Local: " + language)
                }
            }


        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sharedPreferences = getSharedPreferences("Settings", Activity.MODE_PRIVATE)
        val language = sharedPreferences.getString("My_Lang", "")!!.toUpperCase()
        arrangeLocate(language)
        setContentView(R.layout.activity_first)
        languages = resources.getStringArray(R.array.languages)
        arrayAdapter = ArrayAdapter(this, R.layout.dropdown_item, languages)
        autoCompleteTextView = findViewById<AutoCompleteTextView>(R.id.autoCompleteTextView)
        autoCompleteTextView.setAdapter(arrayAdapter)
        if ((language == "RO") || (language == "EN") || (language == "TR")) autoCompleteTextView.setText(
            language
        )

        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)

        println(".....asdaijcşoanca.scaşcşosmvimdfşvmdşfnv")


        //val buttonProducts = findViewById<Button>(R.id.buttonProducts)
        val buttonQuickSell = findViewById<Button>(R.id.buttonQuickSell)
        //val buttonPreviousSales = findViewById<Button>(R.id.buttonPreviousSales)
        val buttonExit = findViewById<Button>(R.id.buttonExit)

        /*
        buttonProducts.setOnClickListener{
            val intent = Intent(this@FirstActivity, MainActivity::class.java)
            startActivity(intent)
        }
        */


        buttonQuickSell.setOnClickListener {
            val intent = Intent(this@FirstActivity, QuickSellActivity::class.java)
            startActivity(intent)
        }
        /*
        buttonPreviousSales.setOnClickListener {
            val intent = Intent(this@FirstActivity, PreviousSalesActivity::class.java)
            startActivity(intent)
        }
         */

        buttonExit.setOnClickListener {
            finishAndRemoveTask() //Finishes all activities in this task and removes it from the recent tasks list.
        }


    }
}