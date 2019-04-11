package com.example.metroparkparkingpill

import android.util.Log
import com.example.metroparkparkingpill.Model.Data
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.net.URL

class DataStorage() {

    var data: Data? = null

    fun FetchData() {

        val retrofit = Retrofit.Builder()
            .baseUrl("http://metroparkparking.tk/").addConverterFactory(GsonConverterFactory.create())
            .build()

        t = Thread {
            while (!Thread.interrupted()) {
                try {
                    val mapper = jacksonObjectMapper()
                    mapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false)
                    data = mapper.readValue<Data>(URL("http://metroparkparking.tk/alldata"))
                    Log.i("lulu", data.toString())

                } catch (e: IOException) {
                    e.printStackTrace();
                    Log.i("Test", e.toString());
                }
                t.interrupt();
            }
        }
        t.start();
    }

    lateinit var t: Thread
}