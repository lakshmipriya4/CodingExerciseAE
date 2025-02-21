package com.example.codingexerciseae.service

import com.example.codingexerciseae.model.Hiring
import retrofit2.Response
import retrofit2.http.GET

interface HiringApi {
    @GET("/hiring.json")
    suspend fun getHiringList(): Response<List<Hiring>>
}