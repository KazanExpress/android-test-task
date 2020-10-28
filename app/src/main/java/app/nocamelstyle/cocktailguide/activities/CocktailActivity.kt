package app.nocamelstyle.cocktailguide.activities

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import app.nocamelstyle.cocktailguide.R
import app.nocamelstyle.cocktailguide.adapters.InredientsAdapter
import app.nocamelstyle.cocktailguide.databinding.ActivityCocktailBinding
import app.nocamelstyle.cocktailguide.models.Drink
import app.nocamelstyle.cocktailguide.models.DrinkRealm
import app.nocamelstyle.cocktailguide.models.Ingredient
import app.nocamelstyle.cocktailguide.services.ApiService
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import io.realm.Realm
import io.realm.kotlin.createObject
import io.realm.kotlin.where
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CocktailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCocktailBinding
    private var isSelected = false
    private lateinit var drink: Drink

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCocktailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        drink = Gson().fromJson(intent.getStringExtra("drink"), Drink::class.java)
        saveToDb()

        Glide.with(this)
            .load(drink.strDrinkThumb)
            .override(binding.imageView.width, binding.imageView.height)
            .into(binding.imageView)

        binding.apply {
            setSupportActionBar(toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setDisplayShowHomeEnabled(true)

            toolbarLayout.title = "${drink.strDrink} (#${drink.idDrink})"

            container.apply {

                val emptyStub = getString(R.string.param_empty)
                drinkType.text = getString(
                        R.string.param_drink_type,
                        drink.strCategory ?: emptyStub
                )
                description.text = getString(
                        R.string.param_drink_description,
                        drink.strDescription ?: emptyStub
                )
                glassType.text = getString(
                        R.string.param_drink_glass,
                        drink.strGlass ?: emptyStub
                )
            }

            fab.setOnClickListener {
                fab.setImageResource(
                        if (!isSelected) R.drawable.ic_baseline_star_rate_24
                        else R.drawable.ic_baseline_star_outline_24
                )
                isSelected = !isSelected
            }

            loadIngredients()
        }
    }

    private fun loadIngredients() {
        binding.loadView.visibility = View.VISIBLE
        GlobalScope.launch(Dispatchers.IO) {
            try {
                onIngredientsLoaded(
                    ApiService.loadIngredients(drink.idDrink ?: "").body()?.ingredients
                )
            } catch (e: Exception) {
                Snackbar
                    .make(binding.root, R.string.internet_error, Snackbar.LENGTH_INDEFINITE)
                    .setAction(R.string.retry) {
                        loadIngredients()
                    }
                    .show()
                onIngredientsLoaded(null)
            }
        }
    }

    private fun onIngredientsLoaded(ingredients: List<Ingredient>?) {
        GlobalScope.launch(Dispatchers.Main) {

            binding.container.apply {
                ingredientsTitle.visibility =
                    if (ingredients?.isNotEmpty() == true) View.VISIBLE else View.GONE
                binding.loadView.visibility = View.GONE
                if (ingredients != null) {

                    ingredientsList.apply {
                        layoutManager = LinearLayoutManager(this@CocktailActivity)
                        adapter = InredientsAdapter(ingredients)
                    }
                }
            }
        }
    }

    private fun saveToDb() {
        Realm.getDefaultInstance().executeTransaction {
            try {
                it.createObject<DrinkRealm>()
            } catch (e: Exception) {}

            val oldDrink =
                it.where<DrinkRealm>().equalTo("idDrink", drink.idDrink).findFirst()

            if (oldDrink == null)
                it.copyToRealm(drink.toRealVersion())
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home)
            finish()
        return super.onOptionsItemSelected(item)
    }

}