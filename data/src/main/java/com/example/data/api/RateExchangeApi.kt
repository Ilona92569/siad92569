package com.example.data.api

import com.example.data.model.CityData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RateExchangeApi {
@GET("api/kursExchange")
    suspend fun getRateExchangeCity(
        @Query("city") city: String
    ): Response<List<CityData>>
}