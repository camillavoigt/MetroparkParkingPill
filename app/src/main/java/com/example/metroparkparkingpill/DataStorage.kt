package com.example.metroparkparkingpill

import android.content.Context
import com.example.metroparkparkingpill.Model.Data
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.net.URL


// Data from API
class DataStorage(context: Context) {

    private lateinit var parkingdata: Data

    companion object : SingletonHolder<DataStorage, Context>(::DataStorage)

    val retrofit = Retrofit.Builder()
        .baseUrl("http://metroparkparking.tk/").addConverterFactory(GsonConverterFactory.create())
        .build()

    /**
     * This method fetch data from the server via the API
     */
    fun fetchData() {

        try {
            val mapper = jacksonObjectMapper()
            mapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false)
            parkingdata = mapper.readValue(URL("http://metroparkparking.tk/alldata"))

        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    /**
     * Receive the fetched data from the API
     */
    fun getData(): Data {
        return parkingdata
    }

}