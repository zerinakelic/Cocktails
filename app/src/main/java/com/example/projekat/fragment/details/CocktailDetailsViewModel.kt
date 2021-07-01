package com.example.projekat.fragment.details

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.projekat.database.asDomainModel
import com.example.projekat.database.getDatabase
import com.example.projekat.network.Model
import com.example.projekat.repository.ModelRepository

class CocktailDetailsViewModel(
    application: Application,
    private val cocktailName: String
) : AndroidViewModel(application) {

    private val repository = ModelRepository(getDatabase(application))
    private val drinksList = repository.drinks

    fun getCocktail(): LiveData<Model> {
        return Transformations.map(drinksList) { it1 ->
            it1.asDomainModel().firstOrNull { it.strDrink == cocktailName }
        }
    }


}