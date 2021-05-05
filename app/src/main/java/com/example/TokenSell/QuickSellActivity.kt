package com.example.TokenSell

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class QuickSellActivity : AppCompatActivity() {

    private lateinit var textViewValue : TextView //This is a promise that we're going to initialize TextView later on.
    private lateinit var restOfTheValue : String //Int'e lateinit yapamıyoruz
    private lateinit var valueDummy : String
    private lateinit var valueDummy2 : String
    private lateinit var buttonHS1 : Button
    private lateinit var buttonHS2 : Button
    private lateinit var buttonHS3 : Button
    private lateinit var buttonHS4 : Button
    private lateinit var buttonHS5 : Button
    private lateinit var buttonHS6 : Button
    private lateinit var buttonHS7 : Button
    private lateinit var buttonHS8 : Button
    private lateinit var buttonArrayList : ArrayList<Button>

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        //binding = ActivityQuickSellBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quick_sell)

        restOfTheValue = ""
        valueDummy = ""
        valueDummy2 = ""

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
        val buttonDoubleZeroZero = findViewById<Button>(R.id.btnDoubleZeroZero)
        val buttonDot = findViewById<Button>(R.id.btnDot)
        val buttonClear = findViewById<Button>(R.id.btnClear)
        val buttonBackClear = findViewById<Button>(R.id.btnBackClear)

        /*
        val stringButtonName = "buttonHS"

        var i: Int = R.id.btnHS1
        var j: Int = R.id.btnHS2
        i++
        val s: String = i.toString()
        println("***" + s + "***" + j)
        */


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

        textViewValue = findViewById(R.id.textViewValue)

        textViewValue.text = ""

        buttonNine.setOnClickListener {
            inputOrganizer("9")
        }

        buttonEight.setOnClickListener {
            inputOrganizer("8")
        }

        buttonSeven.setOnClickListener {
            inputOrganizer("7")
        }

        buttonSix.setOnClickListener {
            inputOrganizer("6")
        }

        buttonFive.setOnClickListener {
            inputOrganizer("5")
        }

        buttonFour.setOnClickListener {
            inputOrganizer("4")
        }

        buttonThree.setOnClickListener {
            inputOrganizer("3")
        }

        buttonTwo.setOnClickListener {
            inputOrganizer("2")
        }

        buttonOne.setOnClickListener {
            inputOrganizer("1")
        }

        buttonZero.setOnClickListener {
            inputOrganizer("0")
        }

        buttonClear.setOnClickListener {
            textViewValue.text = ""
        }

        buttonBackClear.setOnClickListener {
            restOfTheValue = textViewValue.text.toString()
            restOfTheValue = restOfTheValue.dropLast(1)
            textViewValue.text = restOfTheValue
        }

        buttonDot.setOnClickListener {
            restOfTheValue = textViewValue.text.toString()
            if(howManyDotsThere(restOfTheValue) == 0)
            {
                restOfTheValue += "."
                textViewValue.text = restOfTheValue
            }
        }

        buttonHS1.setOnClickListener {
            onlyOneCategorySelected(buttonHS1.tag.toString())
            (it as MaterialButton).apply {
                // set material button background tint list as a single color
                backgroundTintList = ColorStateList.valueOf(Color.rgb(0,118,169))
                // set material button background tint mode
                backgroundTintMode = PorterDuff.Mode.SRC_ATOP
            }

        }
        buttonHS2.setOnClickListener {
            onlyOneCategorySelected(buttonHS2.tag.toString())
            (it as MaterialButton).apply {
                // set material button background tint list as a single color
                backgroundTintList = ColorStateList.valueOf(Color.rgb(0,118,169))
                // set material button background tint mode
                backgroundTintMode = PorterDuff.Mode.SRC_ATOP
            }

        }
        buttonHS3.setOnClickListener {
            onlyOneCategorySelected(buttonHS3.tag.toString())
            (it as MaterialButton).apply {
                // set material button background tint list as a single color
                backgroundTintList = ColorStateList.valueOf(Color.rgb(0,118,169))
                // set material button background tint mode
                backgroundTintMode = PorterDuff.Mode.SRC_ATOP
            }

        }
        buttonHS4.setOnClickListener {
            onlyOneCategorySelected(buttonHS4.tag.toString())
            (it as MaterialButton).apply {
                // set material button background tint list as a single color
                backgroundTintList = ColorStateList.valueOf(Color.rgb(0,118,169))
                // set material button background tint mode
                backgroundTintMode = PorterDuff.Mode.SRC_ATOP
            }

        }
        buttonHS5.setOnClickListener {
            onlyOneCategorySelected(buttonHS5.tag.toString())
            (it as MaterialButton).apply {
                // set material button background tint list as a single color
                backgroundTintList = ColorStateList.valueOf(Color.rgb(0,118,169))
                // set material button background tint mode
                backgroundTintMode = PorterDuff.Mode.SRC_ATOP
            }

        }
        buttonHS6.setOnClickListener {
            onlyOneCategorySelected(buttonHS6.tag.toString())
            (it as MaterialButton).apply {
                // set material button background tint list as a single color
                backgroundTintList = ColorStateList.valueOf(Color.rgb(0,118,169))
                // set material button background tint mode
                backgroundTintMode = PorterDuff.Mode.SRC_ATOP
            }

        }
        buttonHS7.setOnClickListener {
            onlyOneCategorySelected(buttonHS7.tag.toString())
            (it as MaterialButton).apply {
                // set material button background tint list as a single color
                backgroundTintList = ColorStateList.valueOf(Color.rgb(0,118,169))
                // set material button background tint mode
                backgroundTintMode = PorterDuff.Mode.SRC_ATOP
            }

        }
        buttonHS8.setOnClickListener {
            onlyOneCategorySelected(buttonHS8.tag.toString())
            (it as MaterialButton).apply {
                // set material button background tint list as a single color
                backgroundTintList = ColorStateList.valueOf(Color.rgb(0,118,169))
                // set material button background tint mode
                backgroundTintMode = PorterDuff.Mode.SRC_ATOP
            }
        }

    }

    @SuppressLint("SetTextI18n")
    private fun inputOrganizer(valueString: String) {
        var index : Int
        restOfTheValue = textViewValue.text.toString()
        restOfTheValue = eliminateTheUnnecessaryZero(restOfTheValue, valueString)
        println("buradayız 1 " + restOfTheValue)
        if(howManyDotsThere(restOfTheValue)>0) {
            if (isDecimalDigitsLessThanTwo(restOfTheValue)) {
                restOfTheValue += valueString
                textViewValue.text = restOfTheValue

            } else {
                restOfTheValue = restOfTheValue.drop(1)
                index = restOfTheValue.indexOf(".")
                valueDummy2 = restOfTheValue.substring(index + 1)
                valueDummy = restOfTheValue.substring(0, index) + valueDummy2[0] + "." + valueDummy2.drop(1)
                textViewValue.text = valueDummy + valueString
            }
        }
        else if(howManyDotsThere(restOfTheValue) == 0 && !isNumberOfNumDigitsMoreThanFive(restOfTheValue))textViewValue.text = restOfTheValue + valueString
    }

    private fun isDecimalDigitsLessThanTwo(value : String) : Boolean {

        val decimal: String = value.split(".")[1]

        if(decimal.length > 1) {
            return false // return zaten fonksiyonu sonlandırıyor!
        }
            return true
    }

    private fun isNumberOfNumDigitsMoreThanFive(value : String) : Boolean {

        val number: String = value.split(".")[0]

        if(number.length > 4) {
            return true // return zaten fonksiyonu sonlandırıyor!
        }
        return false
    }

    private fun howManyDotsThere(value: String) : Int {
        if(value.contains(".")) return 1
        return 0

    }

    private fun isTheFirstDigitZero(value: String) : Boolean { //Checks if the first digit of the string is zero
        val constantZero : String = "0"
        var dummyString : String = ""
        if(value.isNotEmpty()) {

            dummyString = value.trim()[0].toString()
            println("İlk Değerimiz: " + dummyString)
        }
        if((value.isNotEmpty()) && (dummyString == constantZero)) {
            println("buradayız 2")
            return true}
        return false
    }

    private fun eliminateTheUnnecessaryZero(value : String, stroke : String) : String { //If the first digit is zero and second digit is
        println("hey")
        if(isTheFirstDigitZero(value) && (stroke != ".") && howManyDotsThere(value) == 0)  {
             println("hey 2")
             value.drop(1)
             restOfTheValue = stroke + value
             return restOfTheValue
         }
            return value
    }

    @SuppressLint("ResourceAsColor")
    private fun onlyOneCategorySelected(whichButton: String) {

            println("+++" + whichButton)
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