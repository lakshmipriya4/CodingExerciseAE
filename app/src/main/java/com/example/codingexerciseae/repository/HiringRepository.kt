package com.example.codingexerciseae.repository

import com.example.codingexerciseae.model.Hiring
import retrofit2.Response

interface HiringRepository {

    suspend fun getHiringList(): Response<List<Hiring>>
}