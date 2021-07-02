package com.example.projekat.fragment.details

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class DetailsViewModelFactory(
    private val application: Application,
    private val cocktailName: String,
    private val shareListener: ShareListener
    ) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CocktailDetailsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CocktailDetailsViewModel(application, cocktailName, shareListener) as T
        }
        throw IllegalArgumentException("unable to construct viewmodel")
    }
}