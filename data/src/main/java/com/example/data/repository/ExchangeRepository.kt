package com.example.data.repository

import com.example.data.api.RateExchangeApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.SocketTimeoutException

class ExchangeRepository(private val api: RateExchangeApi) {

    suspend fun getRateExchangeCity(city: String) =
        withContext(Dispatchers.IO) {
            try {
                val response = api.getRateExchangeCity(city)
                if (response.isSuccessful) {
                    return@withContext response.body()
                } else {
                    throw Exception("Ошибка: ${response.code()} ${response.message()}")
                }
            } catch (e: SocketTimeoutException) {
                throw Exception("Время ожидания ответа от сервера истекло. Попробуйте позже.")
            } catch (e: Exception) {
                throw Exception("Ошибка при загрузке данных: ${e.message}")
            }
        }
}
