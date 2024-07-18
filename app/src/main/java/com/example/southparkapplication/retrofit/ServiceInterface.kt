package com.example.southparkapplication.retrofit

import com.example.southparkapplication.CharactersData
import retrofit2.Call
import retrofit2.http.GET

interface ServiceInterface {
    @GET("characters")
    fun getCharacterList(): Call<List<CharactersData>>
}