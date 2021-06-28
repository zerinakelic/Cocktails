package com.example.projekat.network

import com.example.projekat.database.DatabaseModel
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkContainer (val drinks: List<NetworkModel>)

@JsonClass(generateAdapter = true)
data class NetworkModel(
    val idDrink: String,
    val strDrink: String,
    val strCategory: String,
    val strInstructions: String,
    val strDrinkThumb: String
)

fun NetworkContainer.asDomainModel(): List<Model> {
    return drinks.map {
        Model(
            idDrink = it.idDrink,
            strDrink = it.strDrink,
            strCategory = it.strCategory,
            strInstructions = it.strInstructions,
            strDrinkThumb = it.strDrinkThumb
        )
    }
}
fun NetworkContainer.asDatabaseModel(): List<DatabaseModel> {
    return drinks.map {
        DatabaseModel(
            idDrink = it.idDrink,
            strDrink = it.strDrink,
            strCategory = it.strCategory,
            strInstructions = it.strInstructions,
            strDrinkThumb = it.strDrinkThumb
        )
    }
}
