package com.example.ProyectoNoticias.model

import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {
    @GET("everything?domains=wsj.com")
    suspend fun lasNewsList(@Query("apiKey")apiKey: String): NewsApiResult
}