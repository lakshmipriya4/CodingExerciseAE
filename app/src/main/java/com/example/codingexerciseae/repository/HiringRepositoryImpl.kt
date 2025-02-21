package com.example.codingexerciseae.repository

import com.example.codingexerciseae.model.Hiring
import com.example.codingexerciseae.service.HiringApi
import retrofit2.Response
import javax.inject.Inject

class HiringRepositoryImpl @Inject constructor(private val hiringApi: HiringApi): HiringRepository {
    override suspend fun getHiringList(): Response<List<Hiring>> {
        return hiringApi.getHiringList()
    }
}