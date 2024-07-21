package com.example.southparkapplication.retrofit

import com.example.southparkapplication.dataModels.Characters
import retrofit2.Response

import retrofit2.http.GET

interface ServiceInterface {
    @GET("characters")
    suspend fun getCharacterList(): Response<Characters>
}