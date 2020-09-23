package com.yourdevfilmes.service

import com.yourdevfilmes.model.Response
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NYTInterface {

    @GET("reviews/search.json")
    fun listMovies(@Query("api-key") apikey : String): Call<Response>

    @GET("reviews/search.json")
    fun listByText(@Query("api-key") apikey : String, @Query("query") query : String): Call<Response>
}