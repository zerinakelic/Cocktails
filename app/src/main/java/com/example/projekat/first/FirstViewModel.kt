package com.example.projekat.first

import android.app.Application
import androidx.lifecycle.*
import com.example.projekat.database.getDatabase
import com.example.projekat.repository.ModelRepository
import kotlinx.coroutines.launch
import java.io.IOException

class FirstViewModel(application: Application, clickedDrink: String) : AndroidViewModel(application) {

    private val modelRepository = ModelRepository(getDatabase(application), clickedDrink)
    val lista = modelRepository.drinks

    private var _eventNetworkError = MutableLiveData<Boolean>(false)
    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError
    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)
    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    init {
        refreshDataFromRepository()
    }

    private fun refreshDataFromRepository() {

        viewModelScope.launch {
            try {
                modelRepository.refresh()
                _eventNetworkError.value = false
                _isNetworkErrorShown.value = false
            } catch (networkError: IOException) {
                if(lista.value.isNullOrEmpty())
                    _eventNetworkError.value = true

            }
        }
    }
    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }

}
