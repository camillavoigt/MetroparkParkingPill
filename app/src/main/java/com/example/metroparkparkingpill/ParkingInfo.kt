package com.example.metroparkparkingpill

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Html
import android.util.Log
import com.example.metroparkparkingpill.Model.Data
import com.example.metroparkparkingpill.Model.ParkingArea
import com.fasterxml.jackson.databind.DeserializationFeature
import kotlinx.android.synthetic.main.activity_parking_info.*
import org.xml.sax.Parser
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.io.IOException
import com.fasterxml.jackson.module.kotlin.*
import java.net.URL


class ParkingInfo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parking_info)


        val retrofit = Retrofit.Builder()
            .baseUrl("http://metroparkparking.tk/").addConverterFactory(GsonConverterFactory.create())
            .build()

        //val areaInfoService = retrofit.create(AreaInfoService::class.java);
        //New Thread
        t = Thread {
            while (!Thread.interrupted()) {
                //val call = areaInfoService.getAreaInfo("1");
                try {
                    // val response = call.execute().body()!!
                    val mapper = jacksonObjectMapper()
                    mapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false)
                    var parkingArea: Data = mapper.readValue<Data>(URL("http://metroparkparking.tk/alldata"))
                    Log.i("lulu", parkingArea.toString())
                    area_name.post {
                        area_name.text = parkingArea.parkingAreas.get(0).areaName;
                    }


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


    //Web call
    /*
    class AreaInfo(val type: String, val value: Value)

    class Value(
        val areaName: String,
        val areaAddress: String,
        val areaObservationTime: Double,
        val allowedParkingTime: Double
    )

    public interface AreaInfoService {
        @GET("/parkingarea?areaid=1")
        fun getAreaInfo(@Query("areaid") id: String): Call<Value>
    }
     */


}
