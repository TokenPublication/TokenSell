package com.example.TokenSell

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


public class PrefsConfig {

    private val LIST_KEY = "list_key100"

    public fun writeListInPref(context: Context?, arrayList: ArrayList<Sale>) {
        val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context)
        val editor: SharedPreferences.Editor = sharedPrefs.edit()
        val gson = Gson()

        val json = gson.toJson(arrayList)

        editor.putString(LIST_KEY, json)
        editor.commit()
    }

    public fun readListFromPref(context: Context?): ArrayList<Sale> {
        val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context)
        val gson = Gson()
        val json = sharedPrefs.getString(LIST_KEY, "")
        val type = object : TypeToken<ArrayList<Sale?>?>() {}.type
        val arrayList: ArrayList<Sale>? = gson.fromJson<ArrayList<Sale>>(json, type)

        if(arrayList == null) return ArrayList<Sale>()
        else return arrayList
    }

}