package com.example.ubicacionpaises.model

import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {
    @GET("top-headlines?country=us&category=business")
    suspend fun lasNewsList(@Query("apiKey")apiKey: String): NewsApiResult
}