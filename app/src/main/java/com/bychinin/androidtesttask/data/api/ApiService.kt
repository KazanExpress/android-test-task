package com.bychinin.androidtesttask.data.api

import com.bychinin.androidtesttask.data.classes.DetailCocktail
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    // Получение случайного коктейля
    @GET("random.php")
    suspend fun getRandomeCocktail(): DetailCocktail

    // Поиск коктейля
    @GET("search.php")
    suspend fun getSeachCoctail(@Query("s") name : String
    ): DetailCocktail

}