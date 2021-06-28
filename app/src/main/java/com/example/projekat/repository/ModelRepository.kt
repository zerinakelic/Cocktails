package com.example.projekat.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.projekat.database.Baza
import com.example.projekat.database.asDomainModel
import com.example.projekat.network.ApiObject
import com.example.projekat.network.Model
import com.example.projekat.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ModelRepository(private val database: Baza, private val clickedDrink: String) {
    val drinks: LiveData<List<Model>> =
        Transformations.map(database.modelDao.getItemsFromDatabase()) {
            it.asDomainModel()
        }

    suspend fun refresh() {
        withContext(Dispatchers.IO) {
            Log.i("refresh", "refresh videos is called")

            //database.modelDao.deleteAll()
            when (clickedDrink) {
                "zorro" -> {
                    val lista = ApiObject.drinksFromApiObject.getZorro()

                    database.modelDao.insertAll(lista.asDatabaseModel())
                }
                "margarita" -> {
                    val lista = ApiObject.drinksFromApiObject.getMargarita()
                    database.modelDao.insertAll(lista.asDatabaseModel())
                }
                "bijou" -> {
                    val lista = ApiObject.drinksFromApiObject.getBijou()
                    database.modelDao.insertAll(lista.asDatabaseModel())
                }
                "melya" -> {
                    val lista = ApiObject.drinksFromApiObject.getMelya()
                    database.modelDao.insertAll(lista.asDatabaseModel())
                }
            }

        }
    }

}