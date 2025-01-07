package com.example.feature.ui.exchange

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.data.model.CityData
import com.example.data.repository.ExchangeRepository

class ExchangeViewModel(
    private val exchangeRepository: ExchangeRepository
) : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error
    private val _isError = MutableLiveData<Boolean>()
    val isError: LiveData<Boolean> = _isError

    val listCityData = MutableLiveData<List<CityData>>()

    suspend fun getAllTickets(city: String) {
        _isLoading.value = true
        try {
            val data = exchangeRepository.getRateExchangeCity(city)
            if (data != null) {
                listCityData.value = data!!
                _isError.value = false
            } else {
                _error.value = "Данные не получены"
                _isError.value = true
            }
        } catch (e: Exception) {
            _error.value = e.message
            _isError.value = true
        } finally {
            _isLoading.value = false
        }
    }
}