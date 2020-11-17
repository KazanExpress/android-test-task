package com.bychinin.androidtesttask.data.objects

import com.bychinin.androidtesttask.data.classes.ShortDrink

// Синглтон для хранения списка промотренных коктейлей
// Получение - в RandomActivity
// Использование - в MainActivity

object ViewCoctails {

    // Здесь храним
    var viewCocktails : MutableList<ShortDrink> = mutableListOf()

    // Здесь добавляем новый
    fun addCoctail(coctail : ShortDrink){
        viewCocktails.add(coctail)
    }

}