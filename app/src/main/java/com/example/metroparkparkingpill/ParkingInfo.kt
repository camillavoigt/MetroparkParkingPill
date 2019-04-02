package com.example.metroparkparkingpill

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Html
import android.util.Log
import kotlinx.android.synthetic.main.activity_parking_info.*
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.io.IOException


class ParkingInfo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parking_info)


        val retrofit = Retrofit.Builder()
            .baseUrl("http://metroparkparking.tk/").addConverterFactory(GsonConverterFactory.create())
            .build()

        val areaInfoService = retrofit.create(AreaInfoService::class.java);
        Log.i("Test", areaInfoService.toString());

        Log.i("Test", "API her");
        //New Thread
        t = Thread {
            while (!Thread.interrupted()) {
                val call = areaInfoService.getAreaInfo("1");
                Log.i("Test", "API start");
                try {
                    val response = call.execute().body()!!
                    val areaNAme = Html.fromHtml(response.areaName);
                    val areaAddress = Html.fromHtml(response.areaAddress);
                    val areaObservationTime = Html.fromHtml(response.areaObservationTime.toString());
                    val allowedParkingTime = Html.fromHtml(response.allowedParkingTime.toString());
                    Log.i("Test", areaNAme.toString());
                    area_name.post {
                        area_name.text = areaNAme;
                    }
                    area_address.post {
                        area_address.text = areaAddress;
                    }
                    area_observation_time.post {
                        area_observation_time.text = areaObservationTime;
                    }
                    area_parking_time.post {
                        area_parking_time.text = allowedParkingTime;
                    }
                } catch (e: IOException) {
                    e.printStackTrace();
                    Log.i("Test", e.toString());
                }
            }
        }
        t.start();
    }

    lateinit var t: Thread


    //Web call
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
}
