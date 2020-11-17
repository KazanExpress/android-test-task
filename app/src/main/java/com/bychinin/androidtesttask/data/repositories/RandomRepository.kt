package com.bychinin.androidtesttask.data.repositories

import com.bychinin.androidtesttask.data.api.ApiHelper
import com.bychinin.androidtesttask.data.classes.DetailCocktail


class RandomRepository (private val apiHelper: ApiHelper) {

    suspend fun getRandomCoctail() : DetailCocktail = apiHelper.getRandomCoctail()
    suspend fun getSearchCoctail(name : String) : DetailCocktail = apiHelper.getSearchCoctail(name)

}