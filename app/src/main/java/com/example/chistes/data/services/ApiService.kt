package com.example.chistes.data.services

import com.example.chistes.ui.views.models.Chiste
import com.example.chistes.ui.utils.Constanst
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET(Constanst.BASE_CHISTE)
    suspend fun getChistes():Response<Chiste>
}