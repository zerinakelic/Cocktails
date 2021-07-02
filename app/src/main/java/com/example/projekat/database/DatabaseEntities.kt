package com.example.projekat.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.projekat.network.Model

@Entity
data class DatabaseModel constructor(
    @PrimaryKey
    val idDrink: String,
    val strDrink: String,
    val strCategory: String,
    val strInstructions: String,
    val strDrinkThumb: String
)

fun List<DatabaseModel>?.asDomainModel(): List<Model> {
    if (this.isNullOrEmpty()) return arrayListOf()
    return map {
        Model(
            idDrink = it.idDrink,
            strDrink = it.strDrink,
            strCategory = it.strCategory,
            strInstructions = it.strInstructions,
            strDrinkThumb = it.strDrinkThumb
        )
    }
}

fun DatabaseModel.mapOneModel(): Model {
    return Model(
            idDrink = this.idDrink,
            strDrink = this.strDrink,
            strCategory = this.strCategory,
            strInstructions = this.strInstructions,
            strDrinkThumb = this.strDrinkThumb
        )
}