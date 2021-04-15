package com.example.recyclerviewwithcardview

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class QuickSellActivity : AppCompatActivity() {

    private lateinit var textViewValue : TextView //This is a promise that we're going to initialize TextView later on.
    private lateinit var valueString : String
    private lateinit var restOfTheValue : String //Int'e lateinit yapamıyoruz
    private lateinit var valueDummy : String
    private lateinit var valueDummy2 : String

    override fun onCreate(savedInstanceState: Bundle?) {
        //binding = ActivityQuickSellBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quick_sell)

        valueString = ""
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

        //binding.btnNine.text="Turun"

    }

    @SuppressLint("SetTextI18n")
    private fun inputOrganizer(valueString: String) {
        var index : Int
        restOfTheValue = textViewValue.text.toString()
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
        else if(howManyDotsThere(restOfTheValue) == 0 && !isNumberDigitsMoreThanFive(restOfTheValue))textViewValue.text = restOfTheValue + valueString
    }

    private fun isDecimalDigitsLessThanTwo(value : String) : Boolean {

        val decimal: String = value.split(".")[1]

        if(decimal.length > 1) {
            //textViewValue.text = "uuu bu fazla"
            return false // return zaten fonksiyonu sonlandırıyor!
        }
            return true
    }

    private fun isNumberDigitsMoreThanFive(value : String) : Boolean {

        val number: String = value.split(".")[0]

        if(number.length > 4) {
            //textViewValue.text = "uuu bu fazla"
            return true // return zaten fonksiyonu sonlandırıyor!
        }
        return false
    }

    private fun howManyDotsThere(value: String) : Int {
        if(value.contains(".")) return 1
        return 0

    }


}