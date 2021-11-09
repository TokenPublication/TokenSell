package com.example.TokenSell

import android.annotation.SuppressLint
import android.app.Activity
import android.content.ComponentName
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.Typeface
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.gson.Gson
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class QuickSellActivity : AppCompatActivity(), Transformer { //Extending from AppCombatActivity will ensure that all the components work correctly.


    private lateinit var textViewValue : TextView //This is a promise that we're going to initialize TextView later on.
    private lateinit var textViewCategory : TextView //This is a promise that we're going to initialize TextView later on.
    private lateinit var buttonHS1 : Button
    private lateinit var buttonHS2 : Button
    private lateinit var buttonHS3 : Button
    private lateinit var buttonHS4 : Button
    private lateinit var buttonHS5 : Button
    private lateinit var buttonHS6 : Button
    private lateinit var buttonHS7 : Button
    private lateinit var buttonHS8 : Button
    private lateinit var buttonArrayList : ArrayList<Button>
    private lateinit var selectedCategory: String

    private lateinit var theInput: String //This value represents the input entered through keyboard
    private lateinit var json: String // Each time we press the sell button, we're going to fill this object w/ new values

    //The below code is for integration with Payment Gateway
    val PAYMENT_ACTIVITY_RESULT_CODE = 0x1007 // can be anything
    val PAYMENT_PROCESSOR_PACKAGE_NAME = "com.tokeninc.sardis.paymentgateway"
    val PAYMENT_PROCESSOR_APP_NAME = ".pos.PosActivity"
    val TAG_ORDER_BODY = "orderBody"
    var appName: String = PAYMENT_PROCESSOR_PACKAGE_NAME + PAYMENT_PROCESSOR_APP_NAME

    private lateinit var salesList: ArrayList<Sale>
    val prefsConfig = PrefsConfig()

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quick_sell)
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)


        val gson = Gson() // creating a Gson object
        salesList = prefsConfig.readListFromPref(this)

        selectedCategory = ""

        val buttonNine = findViewById<Button>(R.id.btnNine)
        val buttonEight = findViewById<Button>(R.id.btnEight)
        val buttonSeven = findViewById<Button>(R.id.btnSeven)
        val buttonSix = findViewById<Button>(R.id.btnSix)
        val buttonFive = findViewById<Button>(R.id.btnFive)
        val buttonFour = findViewById<Button>(R.id.btnFour)
        val buttonThree = findViewById<Button>(R.id.btnThree)
        val buttonTwo = findViewById<Button>(R.id.btnTwo)
        val buttonOne = findViewById<Button>(R.id.btnOne)
        val buttonZero = findViewById<Button>(R.id.btnZero)
        val buttonEnter = findViewById<Button>(R.id.btnEnter)
        val buttonDoubleZero = findViewById<Button>(R.id.btnDoubleZero)
        val buttonDot = findViewById<Button>(R.id.btnDot)
        val buttonClear = findViewById<Button>(R.id.btnClear)
        val buttonBackClear = findViewById<Button>(R.id.btnBackClear)

        /*
        buttonArrayList  = ArrayList<Button>()

        buttonHS1 = findViewById<Button>(R.id.btnHS1)
        buttonArrayList.add(buttonHS1)
        buttonHS2 = findViewById<Button>(R.id.btnHS2)
        buttonArrayList.add(buttonHS2)
        buttonHS3 = findViewById<Button>(R.id.btnHS3)
        buttonArrayList.add(buttonHS3)
        buttonHS4 = findViewById<Button>(R.id.btnHS4)
        buttonArrayList.add(buttonHS4)
        buttonHS5 = findViewById<Button>(R.id.btnHS5)
        buttonArrayList.add(buttonHS5)
        buttonHS6 = findViewById<Button>(R.id.btnHS6)
        buttonArrayList.add(buttonHS6)
        buttonHS7 = findViewById<Button>(R.id.btnHS7)
        buttonArrayList.add(buttonHS7)
        buttonHS8 = findViewById<Button>(R.id.btnHS8)
        buttonArrayList.add(buttonHS8)

         */

        textViewCategory = findViewById(R.id.textViewCategory)
        textViewValue = findViewById(R.id.textViewValue)
        textViewValue.text = "0" //This is to give an initial value(input) to the QuickSell activity.

        buttonNine.setOnClickListener {
            inputPrinter("9")
        }

        buttonEight.setOnClickListener {
            inputPrinter("8")
        }

        buttonSeven.setOnClickListener {
            inputPrinter("7")
        }

        buttonSix.setOnClickListener {
            inputPrinter("6")
        }

        buttonFive.setOnClickListener {
            inputPrinter("5")
        }

        buttonFour.setOnClickListener {
            inputPrinter("4")
        }

        buttonThree.setOnClickListener {
            inputPrinter("3")
        }

        buttonTwo.setOnClickListener {
            inputPrinter("2")
        }

        buttonOne.setOnClickListener {
            inputPrinter("1")
        }

        buttonZero.setOnClickListener {
            theInput = textViewValue.text.toString()
            if(!(isTheFirstDigitZero()))
            {
                inputPrinter("0")
            }
            else
            {if(ifThereIsADot(theInput)) inputPrinter("0")}
        }

        buttonDoubleZero.setOnClickListener{

                inputPrinter("0")

                    inputPrinter("0")

        }

        buttonClear.setOnClickListener {
            textViewValue.text = ""
        }

        buttonBackClear.setOnClickListener {
            theInput = textViewValue.text.toString()
            //if theInput's last second digit is a ".", then drop two
            //if not
            theInput = theInput.dropLast(1)
            textViewValue.text = theInput
        }

        buttonDot.setOnClickListener {
            theInput= textViewValue.text.toString()
            if(!ifThereIsADot(theInput))
            {
                theInput += "."
                textViewValue.text = theInput
            }
        }

        buttonEnter.setOnClickListener {
            theInput = textViewValue.text.toString()
            if (!(theInput.isEmpty())) {
                val theInputInt = theInput.toDouble()
                println(theInputInt)
                if (selectedCategory == "" && (theInputInt >= 0.01)) {

                    json = gson.toJson(createJSONSalesData(theInput.toDouble()))
                    val intent = Intent()
                    val bundle = Bundle()

                    bundle.putString(TAG_ORDER_BODY, json)
                    intent.putExtras(bundle)
                    intent.component = ComponentName(PAYMENT_PROCESSOR_PACKAGE_NAME, appName)
                    println("JSON: " + json)
                    startActivityForResult(intent, PAYMENT_ACTIVITY_RESULT_CODE)
                } else {
                    textViewCategory.setTextColor(getColor(R.color.red))
                    textViewCategory.setTypeface(Typeface.DEFAULT_BOLD)
                    if (selectedCategory != "") {
                        textViewCategory.text =  getString(R.string.first_select_a_category)
                    }
                    if (theInputInt < 0.01) {
                        textViewCategory.text = getString(R.string.please_enter_amount)
                    }
                }

                /*
                  val theInputInt = theInput.toDouble()
                println(theInputInt)
                if (selectedCategory != "none" && (theInputInt >= 0.01)) {

                    json = gson.toJson(createJSONSalesData(theInput.toDouble(), selectedCategory))
                    val intent = Intent()
                    val bundle = Bundle()

                    bundle.putString(TAG_ORDER_BODY, json)
                    intent.putExtras(bundle)
                    intent.component = ComponentName(PAYMENT_PROCESSOR_PACKAGE_NAME, appName)
                    startActivityForResult(intent, PAYMENT_ACTIVITY_RESULT_CODE)
                } else {
                    textViewCategory.setTextColor(getColor(R.color.red))
                    textViewCategory.setTypeface(Typeface.DEFAULT_BOLD)
                    if (selectedCategory == "none") {
                        textViewCategory.text = getString(R.string.first_select_a_category)
                    }
                    if (theInputInt < 0.01) {
                        textViewCategory.text = getString(R.string.please_enter_amount)
                    }
                }
                */
            }
            else textViewCategory.text = getString(R.string.please_enter_amount)
        }
        /*
        buttonHS1.setOnClickListener {
            onlyOneCategorySelected(buttonHS1.text.toString())

            (it as MaterialButton).apply {
                // set material button background tint list as a single color
                backgroundTintList = ColorStateList.valueOf(Color.rgb(0,118,169))
                // set material button background tint mode
                backgroundTintMode = PorterDuff.Mode.SRC_ATOP
            }

        }
        buttonHS2.setOnClickListener {
            onlyOneCategorySelected(buttonHS2.text.toString())

            (it as MaterialButton).apply {
                // set material button background tint list as a single color
                backgroundTintList = ColorStateList.valueOf(Color.rgb(0,118,169))
                // set material button background tint mode
                backgroundTintMode = PorterDuff.Mode.SRC_ATOP
            }

        }
        buttonHS3.setOnClickListener {
            onlyOneCategorySelected(buttonHS3.text.toString())

            (it as MaterialButton).apply {
                // set material button background tint list as a single color
                backgroundTintList = ColorStateList.valueOf(Color.rgb(0,118,169))
                // set material button background tint mode
                backgroundTintMode = PorterDuff.Mode.SRC_ATOP
            }

        }
        buttonHS4.setOnClickListener {
            onlyOneCategorySelected(buttonHS4.text.toString())

            (it as MaterialButton).apply {
                // set material button background tint list as a single color
                backgroundTintList = ColorStateList.valueOf(Color.rgb(0,118,169))
                // set material button background tint mode
                backgroundTintMode = PorterDuff.Mode.SRC_ATOP
            }

        }
        buttonHS5.setOnClickListener {
            onlyOneCategorySelected(buttonHS5.text.toString())

            (it as MaterialButton).apply {
                // set material button background tint list as a single color
                backgroundTintList = ColorStateList.valueOf(Color.rgb(0,118,169))
                // set material button background tint mode
                backgroundTintMode = PorterDuff.Mode.SRC_ATOP
            }

        }
        buttonHS6.setOnClickListener {
            onlyOneCategorySelected(buttonHS6.text.toString())

            (it as MaterialButton).apply {
                // set material button background tint list as a single color
                backgroundTintList = ColorStateList.valueOf(Color.rgb(0,118,169))
                // set material button background tint mode
                backgroundTintMode = PorterDuff.Mode.SRC_ATOP
            }

        }
        buttonHS7.setOnClickListener {
            onlyOneCategorySelected(buttonHS7.text.toString())

            (it as MaterialButton).apply {
                // set material button background tint list as a single color
                backgroundTintList = ColorStateList.valueOf(Color.rgb(0,118,169))
                // set material button background tint mode
                backgroundTintMode = PorterDuff.Mode.SRC_ATOP
            }

        }
        buttonHS8.setOnClickListener {
            onlyOneCategorySelected(buttonHS8.text.toString())

            (it as MaterialButton).apply {
                // set material button background tint list as a single color
                backgroundTintList = ColorStateList.valueOf(Color.rgb(0,118,169))
                // set material button background tint mode
                backgroundTintMode = PorterDuff.Mode.SRC_ATOP
            }
        }

         */

    }

    private fun inputPrinter(value:String) {
        theInput = textViewValue.text.toString()
        //first check whether there is dot typed already i.e. fun ifThereIsADot(). If not
        if(!ifThereIsADot(theInput))
        {
            //check if the number of num digits are less than 4 i.e. fun howManyNumDigits() < 4, then
            if(howManyNumDigits(theInput) < 3)  if(isTheFirstDigitZero()) theInput = theInput.drop(1) + value else theInput += value
            //else do nothing
        }
        else
        {
            //check if the number of decimal digits are less than 2 i.e. fun howManyDecDigits() < 2, then
            if(howManyDecimalDigits(theInput) < 2) theInput = theInput + value
            //else do nothing
        }
        textViewValue.text = theInput
    }

    private fun ifThereIsADot(value: String) : Boolean {
        if(value.contains(".")) return true
        return false
    }

    private fun howManyNumDigits(value: String) : Int {
        if(ifThereIsADot(value)) {
            val numPart: String = value.split(".")[0]
            return numPart.length
        }
        else
        {
            return value.length
        }
    }

    private fun howManyDecimalDigits(value: String) : Int {
        if(ifThereIsADot(value)) {
            val numPart: String = value.split(".")[1]
            return numPart.length
        }
        else
        {
            return 0
        }
    }

    private fun isTheFirstDigitZero() : Boolean {
        theInput = textViewValue.text.toString().trim()
        if(theInput.equals(""))
        {
        println("So the first digit is not zero. Cuzz there is no input typed.")
        return false
        }
        else
        {
            if(theInput[0] == '0') {
                return true
            }
            else return false
        }
    }
/*
    fun createJSONSalesData(amount: Double, category: String, taxPercent: Double=8.0, quantity: Double=1.0):FinalModel {
        val theInputDouble = textViewValue.text.toString().toDouble()
        val formattedModels: MutableList<MinJSONModel> = mutableListOf<MinJSONModel>()
        val onlyCustomerInfo: CustomerInfo = CustomerInfo("99999999999", "George Ivan", "1st District", "Bucureşti", "România")

        formattedModels.add(MinJSONModel(
            category,
            (amount * 100).toInt(),
            (taxPercent * 100).toInt(),
            (quantity * 1000).toInt()
        ))

        if(theInputDouble > 500)
        return FinalModel(onlyCustomerInfo, formattedModels)
        else return FinalModel(null, formattedModels)
    }

    */

    fun createJSONSalesData(amount: Double):MinJSONModel {
        val theInputDouble = textViewValue.text.toString().toDouble()
        val formattedModels: MutableList<MinJSONModel> = mutableListOf<MinJSONModel>()
        val onlyCustomerInfo: CustomerInfo = CustomerInfo("99999999999", "George Ivan", "1st District", "Bucureşti", "România")

        formattedModels.add(MinJSONModel(

            (amount * 100).toInt())

        )

        if(theInputDouble > 500)
            return MinJSONModel((amount * 100).toInt())
        else return MinJSONModel((amount * 100).toInt())
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


            //salesList.add(0, eachSale) //Her yeni satışı listenin en başına ekliyoruz.
            prefsConfig.writeListInPref(this, salesList)
            println("normal *****_ $message")
            //Satıştan sonra ana ekrana atmak için
            val intent = Intent(this@QuickSellActivity, FirstActivity::class.java)
            startActivity(intent)


        }
    }


    @SuppressLint("ResourceAsColor")
    private fun onlyOneCategorySelected(whichButton: String) {

            selectedCategory = whichButton
            textViewCategory.setTextColor(getColor(R.color.token_blue))
            textViewCategory.setTypeface(Typeface.DEFAULT_BOLD)
            textViewCategory.text = whichButton
            buttonArrayList.forEach {
                (it as MaterialButton).apply {
                    // set material button background tint list as a single color
                    //backgroundTintList = ColorStateList.valueOf(Color.rgb(0,118,169))
                    backgroundTintList = ColorStateList.valueOf(Color.rgb(98,0,238))
                    // set material button background tint mode
                    backgroundTintMode = PorterDuff.Mode.SRC_ATOP
                }
            }

    }


}