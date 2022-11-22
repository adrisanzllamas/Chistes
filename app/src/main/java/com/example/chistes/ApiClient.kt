package com.example.chistes

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