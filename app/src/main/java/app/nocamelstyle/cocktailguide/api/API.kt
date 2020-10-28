package app.nocamelstyle.cocktailguide.api

import app.nocamelstyle.cocktailguide.models.AnswerCategories
import app.nocamelstyle.cocktailguide.models.AnswerDrinks
import app.nocamelstyle.cocktailguide.models.AnswerIngredients
import retrofit2.Call
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query

interface API {

    @GET("random.php")
    fun getRandom():
            Call<AnswerDrinks>

    @GET("lookup.php")
    fun getIngredients(@Query("iid") drinkId: String):
            Call<AnswerIngredients>

    @GET("search.php")
    fun searchDrinks(@Query("s") drinkName: String):
            Call<AnswerDrinks>

    @GET("filter.php")
    fun getDrinks(@Query("c") categotyName: String):
            Call<AnswerDrinks>

    @GET("list.php?c=list")
    fun getCategories():
            Call<AnswerCategories>

    companion object {
        val client by lazy { retrofit.create<API>() }
    }

}