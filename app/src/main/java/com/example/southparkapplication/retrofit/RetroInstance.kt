package com.example.southparkapplication.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetroInstance {

        val api : ServiceInterface by lazy {
            Retrofit.Builder()
                .baseUrl(Utils.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ServiceInterface::class.java)
        }

        }


