package com.yourdevfilmes.service

import com.google.gson.GsonBuilder
import com.yourdevfilmes.model.Response
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NYTService {

    private val URL_BASE = "https://api.nytimes.com/svc/movies/v2/"
    private val APIKEY = "z1TcvkkGzc2Zm0CLAaxHrge1IdRQiUrH"

    private val gson = GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create()

    private val api = Retrofit.Builder().baseUrl(URL_BASE)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
        .create(NYTInterface::class.java)

    suspend fun getData() : Call<Response> = api.listMovies(APIKEY)

    suspend fun getByText(text: String): Call<Response> = api.listByText(APIKEY, text)
}