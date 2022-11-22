package com.example.chistes

import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET(Constanst.BASE_CHISTE)
    suspend fun getChistes():Response<Chiste>
}