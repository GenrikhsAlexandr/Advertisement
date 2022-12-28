package com.GenrikhsAlexandr.myapplication.utils


import android.content.Context
import com.GenrikhsAlexandr.myapplication.R
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.io.InputStream


object City {
    fun getAllCountry(context: Context): ArrayList<String> {
        val tempArray = ArrayList<String>()

        try {
            val inputStream: InputStream = context.assets.open("countriesToCities.json")
            val size: Int = inputStream.available()
            val bytesArray = ByteArray(size)
            inputStream.read(bytesArray)
            val jsonFile = String(bytesArray)
            val jsonObject = JSONObject(jsonFile)
            val countryName = jsonObject.names()
            if (countryName != null) {
                for (n in 0..countryName.length())
                    tempArray.add(countryName.getString(n))
            }
        } catch (_: JSONException) {
        }
        return tempArray
    }

    fun filterListCountry(list: ArrayList<String>, searchText: String?): ArrayList<String> {
        val tempList = ArrayList<String>()
        tempList.clear()
        if (searchText == null){
            tempList.add(R.string.no_result.toString())
            return tempList
        }
        for (selection: String in list){
            if (selection.lowercase().startsWith(searchText.lowercase()))
                tempList.add(selection)
        }
        if (tempList.size == 0)tempList.add(R.string.no_result.toString())
        return tempList

    }
}