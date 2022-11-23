package com.example.chistes.data.services

import com.example.chistes.ui.utils.Constanst
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {

    fun getApiClient(): Retrofit {
        var retrofit= Retrofit.Builder().baseUrl(Constanst.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit
    }
}