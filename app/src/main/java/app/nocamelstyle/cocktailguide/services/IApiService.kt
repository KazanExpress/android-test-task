package app.nocamelstyle.cocktailguide.services

import app.nocamelstyle.cocktailguide.models.AnswerCategories
import app.nocamelstyle.cocktailguide.models.AnswerDrinks
import app.nocamelstyle.cocktailguide.models.AnswerIngredients
import app.nocamelstyle.cocktailguide.models.Drink
import retrofit2.Call
import retrofit2.Response

interface IApiService {

    fun loadRandomDrink(): Response<AnswerDrinks>

    fun loadIngredients(drinkId: String): Response<AnswerIngredients>

    fun searchDrinks(drinkName: String): Response<AnswerDrinks>

    fun loadDrinks(categotyName: String): Response<AnswerDrinks>

    fun loadCategories(): Response<AnswerCategories>

}