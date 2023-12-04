package com.valencio.pawsomepets.networkCalls

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetroInstance {

    companion object {
        val baseURL = "https://api.thedogapi.com/v1/"

        fun getRetroInstance(): Retrofit {
            return Retrofit.Builder().baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create()).build()
        }
    }
}