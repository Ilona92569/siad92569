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
    private val _isError = MutableLiveData<Boolean>(false)
    val isError: LiveData<Boolean> = _isError

    private val _selectedCity = MutableLiveData<String>("Брест")
    val selectedCity : LiveData<String> = _selectedCity

    private val _listCityData = MutableLiveData<List<CityData>>()
    val listCityData : LiveData<List<CityData>> = _listCityData

    suspend fun getAllTickets(city: String) {
        _listCityData.value = listOf()
        _isLoading.value = true
        try {
            val data = exchangeRepository.getRateExchangeCity(city)
            if (data != null) {
                _listCityData.value = data!!
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

    fun exchangeCity(city: String){
        _selectedCity.value = city
    }
}