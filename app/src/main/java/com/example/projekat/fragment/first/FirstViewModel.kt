package com.example.projekat.fragment.first

import android.app.Application
import androidx.lifecycle.*
import com.example.projekat.database.DatabaseModel
import com.example.projekat.database.asDomainModel
import com.example.projekat.database.getDatabase
import com.example.projekat.network.Model
import com.example.projekat.repository.ModelRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class FirstViewModel(application: Application, private val clickedDrink: String) : AndroidViewModel(application) {

    private val modelRepository = ModelRepository(getDatabase(application))
    private val drinksList = modelRepository.drinks

    private val _visible: MutableLiveData<Boolean> = MutableLiveData(false)
    val listVisible: LiveData<Boolean>
    get() = _visible


    init {
        refreshDataFromRepository()
    }

    fun refreshDataFromRepository() {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                fetchCocktails()
            }
            _visible.postValue(true)
        }
    }

    private suspend fun fetchCocktails() {
        modelRepository.fetchCocktails(clickedDrink)
    }

    fun getAllDrinks(): LiveData<List<Model>> {
        return Transformations.map(drinksList) {
            it?.asDomainModel()
        }
    }
}