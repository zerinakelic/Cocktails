package com.example.projekat.fragment.first

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class FirstViewModelFactory(private val application: Application, private val clickedDrink: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(FirstViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FirstViewModel(application, clickedDrink) as T
        }
        throw IllegalArgumentException("Unable to construct viewmodel")
    }

}