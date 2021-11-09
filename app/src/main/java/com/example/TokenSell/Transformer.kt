package com.example.TokenSell

import android.content.res.Resources

interface Transformer {
    fun convertStatus(message: String?): String {

        val status = message!!.substringAfter("s\":").substring(0, 1)

        println("message: " + message)

        when(status)
        {
            "0" -> return "Sale Successful"
            "-1"-> return "User Canceled"
            "1" -> return "Missing Space"
            "2" -> return "Invalid EInvoice Customer"
            "3" -> return "Integrator Error"
            "4" -> return "Network Error"
        }
        return "Sale Failed"
    }

    fun convertInvNo(message: String?): String {

        val invNO = message!!.substringAfter("iceID\":").substring(1, 17)
        println(invNO)
        return invNO
    }

    fun catchPaymentCount(message: String?): Int {

        val paymentCount = message!!.substringAfter("tCount\":").substring(0, 1)
        return paymentCount.toInt()
    }

    fun totalAmount(message: String?): Double {

        var dummyMessage: String = message!!
        var dummy: Int = 0

        for (i in 1..catchPaymentCount(message)) {
            dummy += dummyMessage.substringAfter("amount\":").substring(0, 10).substringBefore(",")
                .toInt()
            dummyMessage = dummyMessage.substringAfter("amount\":")
            println("*** $dummy") //E tabi bu hep ilk amount'u okuyor!!!
        }
        println("TotalInt: $dummy")
        return ((dummy.toDouble()) / 100)
    }

    fun getPaymentType(message: String?): String {

        if (catchPaymentCount(message) > 1) return "Cash + Credit Card"
        else {
            val paymentType =
                message!!.substringAfter("cription\":").substring(1, 15).substringBefore("\"")
            return paymentType
        }

    }
}