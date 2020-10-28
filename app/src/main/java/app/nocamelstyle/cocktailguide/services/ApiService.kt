package app.nocamelstyle.cocktailguide.services

import app.nocamelstyle.cocktailguide.api.API
import app.nocamelstyle.cocktailguide.models.AnswerCategories
import app.nocamelstyle.cocktailguide.models.AnswerIngredients
import app.nocamelstyle.cocktailguide.models.Drink
import retrofit2.Response

object ApiService : IApiService {

    override fun loadRandomDrink() =
            API.client.getRandom().execute()

    override fun loadIngredients(drinkId: String) =
            API.client.getIngredients(drinkId).execute()

    override fun searchDrinks(drinkName: String) =
            API.client.searchDrinks(drinkName).execute()

    override fun loadDrinks(categotyName: String) =
            API.client.getDrinks(categotyName).execute()

    override fun loadCategories() =
            API.client.getCategories().execute()

}