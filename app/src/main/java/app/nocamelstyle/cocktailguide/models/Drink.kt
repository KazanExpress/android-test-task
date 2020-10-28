package app.nocamelstyle.cocktailguide.models

import io.realm.RealmObject

data class Drink(
    val idDrink: String? = null,
    val strDrink: String? = null,
    val strCategory: String? = null,
    val strDrinkThumb: String? = null,
    val strDescription: String? = null,
    val strGlass: String? = null,
    val strIngredient1: String? = null,
    val strIngredient2: String? = null,
    val strIngredient3: String? = null,
    val strIngredient4: String? = null,
    val strIngredient5: String? = null,
    val strIngredient6: String? = null,
    val strIngredient7: String? = null,
    val strIngredient8: String? = null,
    val strIngredient9: String? = null,
    val strIngredient10: String? = null,
    val strIngredient11: String? = null,
    val strIngredient12: String? = null,
    val strIngredient13: String? = null,
    val strIngredient14: String? = null,
    val strIngredient15: String? = null
) {
    fun toRealVersion() = DrinkRealm(idDrink, strDrink, strCategory, strDrinkThumb, strDescription, strGlass, strIngredient1, strIngredient2, strIngredient3, strIngredient4, strIngredient5, strIngredient6, strIngredient7, strIngredient8, strIngredient9, strIngredient10, strIngredient11, strIngredient12, strIngredient13, strIngredient14, strIngredient15)
}

open class DrinkRealm(
    var idDrink: String? = null,
    var strDrink: String? = null,
    var strCategory: String? = null,
    var strDrinkThumb: String? = null,
    var strDescription: String? = null,
    var strGlass: String? = null,
    var strIngredient1: String? = null,
    var strIngredient2: String? = null,
    var strIngredient3: String? = null,
    var strIngredient4: String? = null,
    var strIngredient5: String? = null,
    var strIngredient6: String? = null,
    var strIngredient7: String? = null,
    var strIngredient8: String? = null,
    var strIngredient9: String? = null,
    var strIngredient10: String? = null,
    var strIngredient11: String? = null,
    var strIngredient12: String? = null,
    var strIngredient13: String? = null,
    var strIngredient14: String? = null,
    var strIngredient15: String? = null
) : RealmObject() {
    fun toBasicVersion() = Drink(idDrink, strDrink, strCategory, strDrinkThumb, strDescription, strGlass, strIngredient1, strIngredient2, strIngredient3, strIngredient4, strIngredient5, strIngredient6, strIngredient7, strIngredient8, strIngredient9, strIngredient10, strIngredient11, strIngredient12, strIngredient13, strIngredient14, strIngredient15)
}