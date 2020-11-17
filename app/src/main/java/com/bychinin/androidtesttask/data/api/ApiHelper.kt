package com.bychinin.androidtesttask.data.api

import com.bychinin.androidtesttask.data.classes.DetailCocktail

class ApiHelper(private val apiService: ApiService) {

    suspend fun getRandomCoctail() : DetailCocktail = apiService.getRandomeCocktail()
    suspend fun getSearchCoctail(name : String) : DetailCocktail = apiService.getSeachCoctail(name)

}