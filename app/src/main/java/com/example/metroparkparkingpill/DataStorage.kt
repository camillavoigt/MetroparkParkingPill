package com.example.metroparkparkingpill

import android.content.Context
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
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType


// Data from API
class DataStorage(context: Context) {

    private lateinit var parkingdata: Data

    companion object : SingletonHolder<DataStorage, Context>(::DataStorage)

    fun FetchData() {

        val retrofit = Retrofit.Builder()
            .baseUrl("http://metroparkparking.tk/").addConverterFactory(GsonConverterFactory.create())
            .build()

        try {
            val mapper = jacksonObjectMapper()
            mapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false)
            parkingdata = mapper.readValue<Data>(URL("http://metroparkparking.tk/alldata"))


        } catch (e: IOException) {
            e.printStackTrace();
        }
    }

    fun getData(): Data {
        return parkingdata
    }

}