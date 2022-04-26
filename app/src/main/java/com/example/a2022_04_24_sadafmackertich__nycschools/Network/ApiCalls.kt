package com.example.a2022_04_24_sadafmackertich__nycschools.Network

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiCalls {

    @GET("s3k6-pzi2.json")
    fun getSchools(): Call<List<SchoolListDataObject>>

    @GET("f9bf-2cp4.json")
    fun getSatScores(@Query("dbn", encoded = false) dbn:String): Call<List<SchoolSATDataObject>>

    companion object {

        var BASE_URL = "https://data.cityofnewyork.us/resource/"

        fun create(): ApiCalls {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()

            return retrofit.create(ApiCalls::class.java)
        }

    }

}

