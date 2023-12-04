package com.valencio.pawsomepets.networkCalls

import com.valencio.pawsomepets.models.RecyclerList
import retrofit2.http.GET
import retrofit2.http.Query

interface RetroService {
    @GET("repositories")
    suspend fun getDataFromApi(@Query("/images") query: String): RecyclerList

    /** Was Stuck in middle while using this approach so continued the remaining process using Volley API
     * (Which I was more familiar about)*/
}