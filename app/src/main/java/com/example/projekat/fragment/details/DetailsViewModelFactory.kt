package com.example.projekat.fragment.details

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class DetailsViewModelFactory(private val application: Application, val cocktailName: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CocktailDetailsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CocktailDetailsViewModel(application, cocktailName) as T
        }
        throw IllegalArgumentException("unable to construct viewmodel")
    }
}