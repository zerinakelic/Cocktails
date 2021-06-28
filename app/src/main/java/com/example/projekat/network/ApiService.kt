package com.example.projekat.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET


private const val BASE_URL = "https://www.thecocktaildb.com/api/json/v1/1/"
interface ApiService {

    @GET("search.php?s=margarita")
    suspend fun getMargarita(): NetworkContainer //vraca objekat koji sadrzi listu drinks

    @GET("search.php?s=zorro")
    suspend fun getZorro(): NetworkContainer

    @GET("search.php?s=bijou")
    suspend fun getBijou(): NetworkContainer

    @GET("search.php?s=melya")
    suspend fun getMelya(): NetworkContainer
}
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

object ApiObject {
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    val drinksFromApiObject = retrofit.create(ApiService::class.java)
}