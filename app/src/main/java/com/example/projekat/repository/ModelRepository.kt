package com.example.projekat.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.projekat.database.Baza
import com.example.projekat.database.DatabaseModel
import com.example.projekat.network.ApiObject
import com.example.projekat.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

class ModelRepository(private val database: Baza) {
    private val dao = database.modelDao

    var drinks: LiveData<List<DatabaseModel>> = dao.getItemsFromDatabase()

    suspend fun fetchCocktails(clickedDrink: String) {
        withContext(Dispatchers.IO) {
            async {
                Log.i("refresh", "fetch cocktails is called")

                val lista = ApiObject.drinksFromApiObject.getCocktail(clickedDrink)
                Log.i("lista", lista.toString())

                dao.deleteAll()
                dao.insertAll(lista.asDatabaseModel())
                drinks = dao.getItemsFromDatabase()
            }
        }
    }
}
