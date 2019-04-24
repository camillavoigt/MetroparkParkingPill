package com.example.metroparkparkingpill

import android.text.Html
import android.util.Log
import com.example.metroparkparkingpill.Model.Data
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.net.URL


// Data from API
class DataStorage() {

    fun FetchData(): Data {

        val retrofit = Retrofit.Builder()
            .baseUrl("http://metroparkparking.tk/").addConverterFactory(GsonConverterFactory.create())
            .build()

        try {
            val mapper = jacksonObjectMapper()
            mapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false)
            val data = mapper.readValue<Data>(URL("http://metroparkparking.tk/alldata"))

            return data

        } catch (e: IOException) {
            e.printStackTrace();
        }

        return Data(ArrayList())
    }


}