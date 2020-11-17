package com.bychinin.androidtesttask.UI.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bychinin.androidtesttask.R
import com.bychinin.androidtesttask.UI.factories.RandomModelFactory
import com.bychinin.androidtesttask.UI.models.RandomViewModel
import com.bychinin.androidtesttask.data.api.ApiHelper
import com.bychinin.androidtesttask.data.api.RetrofitBuilder
import com.bychinin.androidtesttask.data.classes.DetailCocktail
import com.bychinin.androidtesttask.data.classes.ShortDrink
import com.bychinin.androidtesttask.data.objects.ViewCoctails
import com.bychinin.androidtesttask.databinding.ActivityRandomBinding
import com.bychinin.androidtesttask.utils.Status
import com.squareup.picasso.Picasso

class RandomActivity : AppCompatActivity() {

    private lateinit var randomViewModel: RandomViewModel
    private lateinit var binding : ActivityRandomBinding
    private var isLike : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRandomBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Начало работы
        val name : String = intent.getStringExtra("name") ?: ""
        getCoctail(name)

        // Повтор при ошибке
        binding.btnRetry.setOnClickListener {
            getCoctail(null)
        }

        // Swype
        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.swipeRefreshLayout.isRefreshing = false
            getCoctail(null)
        }

        // like
        binding.ivLike.setOnClickListener {
            if (isLike) {
                binding.ivLike.setImageResource(R.drawable.ic_star)
                isLike = false
            } else {
                binding.ivLike.setImageResource(R.drawable.ic_full_star)
                isLike = true
            }
        }

        // back
        binding.ivBack.setOnClickListener {
            finish()
        }

    }

        private fun getCoctail(name : String?){

            isLike = false

            randomViewModel = ViewModelProviders
                .of(this, RandomModelFactory(ApiHelper(RetrofitBuilder.apiService)))
                .get(RandomViewModel::class.java)

            when (name) {
                null -> {
                    // Получение случайного коктейля при ошибке или при свайпе
                    randomViewModel.getRandomCoctail().observe(this, Observer {
                        it?.let { resource ->
                            when (resource.status) {
                                Status.SUCCESS -> {
                                    UIshowSuccess()
                                    resource.data?.let {
                                            coctail -> if (coctail.drinks == null)
                                    { Toast.makeText(this, getString(R.string.not_found), Toast.LENGTH_SHORT).show() }
                                    else
                                    { retrieveList(coctail)}
                                    }
                                }
                                Status.ERROR -> {
                                    UIshowError()
                                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                                }
                                Status.LOADING -> {
                                    UIshowLoading()
                                }
                            }
                        }
                    })
                }
                else -> {
                    // Поиск коктейля
                    randomViewModel.getSearchCoctail(name).observe(this, Observer {
                        it?.let { resource ->
                            when (resource.status) {
                                Status.SUCCESS -> {
                                    UIshowSuccess()
                                    resource.data?.let {
                                            coctail -> if (coctail.drinks == null)
                                    { Toast.makeText(this, getString(R.string.not_found), Toast.LENGTH_SHORT).show() }
                                    else
                                    { retrieveList(coctail)}
                                    }
                                }
                                Status.ERROR -> {
                                    UIshowError()
                                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                                }
                                Status.LOADING -> {
                                    UIshowLoading()
                                }
                            }
                        }
                    })
                }
            }

    }

        private fun retrieveList(cocktail: DetailCocktail) {

            // Сохранение коктейля в синглтон
            ViewCoctails.addCoctail(ShortDrink(cocktail.drinks[0].strDrink, cocktail.drinks[0].strDrinkThumb))

            // Заполняем экран данными
            binding.ivLike.setImageResource(R.drawable.ic_star)
            Picasso
                .get()
                .load(cocktail.drinks[0].strDrinkThumb)
                .placeholder(R.color.purple_500)
                .into(binding.ivLogo)
            binding.tvName.text = getString(R.string.name) + cocktail.drinks[0].strDrink
            binding.tvId.text = getString(R.string.id) + cocktail.drinks[0].idDrink
            binding.tvCategory.text = getString(R.string.cat) + cocktail.drinks[0].strCategory
            binding.tvTypeCoctail.text = getString(R.string.type) + cocktail.drinks[0].strAlcoholic
            binding.tvTypeGlass.text = getString(R.string.glass) + cocktail.drinks[0].strGlass

            var ingr : String = "Ingredients:\n"
            if (cocktail.drinks[0].strIngredient1 != null) {ingr = ingr + cocktail.drinks[0].strIngredient1 + "\n"}
            if (cocktail.drinks[0].strIngredient2 != null) {ingr = ingr + cocktail.drinks[0].strIngredient2 + "\n"}
            if (cocktail.drinks[0].strIngredient3 != null) {ingr = ingr + cocktail.drinks[0].strIngredient3 + "\n"}
            if (cocktail.drinks[0].strIngredient4 != null) {ingr = ingr + cocktail.drinks[0].strIngredient4 + "\n"}
            if (cocktail.drinks[0].strIngredient5 != null) {ingr = ingr + cocktail.drinks[0].strIngredient5 + "\n"}
            if (cocktail.drinks[0].strIngredient6 != null) {ingr = ingr + cocktail.drinks[0].strIngredient6 + "\n"}
            if (cocktail.drinks[0].strIngredient7 != null) {ingr = ingr + cocktail.drinks[0].strIngredient7 + "\n"}
            if (cocktail.drinks[0].strIngredient8 != null) {ingr = ingr + cocktail.drinks[0].strIngredient8 + "\n"}
            if (cocktail.drinks[0].strIngredient9 != null) {ingr = ingr + cocktail.drinks[0].strIngredient9 + "\n"}
            if (cocktail.drinks[0].strIngredient10 != null) {ingr = ingr + cocktail.drinks[0].strIngredient10 + "\n"}
            if (cocktail.drinks[0].strIngredient11 != null) {ingr = ingr + cocktail.drinks[0].strIngredient11 + "\n"}
            if (cocktail.drinks[0].strIngredient12 != null) {ingr = ingr + cocktail.drinks[0].strIngredient12 + "\n"}
            if (cocktail.drinks[0].strIngredient13 != null) {ingr = ingr + cocktail.drinks[0].strIngredient13 + "\n"}
            if (cocktail.drinks[0].strIngredient14 != null) {ingr = ingr + cocktail.drinks[0].strIngredient14 + "\n"}
            if (cocktail.drinks[0].strIngredient15 != null) {ingr = ingr + cocktail.drinks[0].strIngredient15 + "\n"}
            binding.tvIngredients.text = ingr
    }

    private fun UIshowLoading(){
        // Элементы при загрузке

        // VISIBLE
        binding.pbMain.visibility = View.VISIBLE
        // INVISIBLE
        binding.btnRetry.visibility = View.INVISIBLE
        binding.ivLogo.visibility = View.INVISIBLE
        binding.tvName.visibility = View.INVISIBLE
        binding.tvId.visibility = View.INVISIBLE
        binding.tvCategory.visibility = View.INVISIBLE
        binding.tvTypeCoctail.visibility = View.INVISIBLE
        binding.tvTypeGlass.visibility = View.INVISIBLE
        binding.tvIngredients.visibility = View.INVISIBLE
        binding.ivLike.visibility = View.INVISIBLE
        binding.ivBack.visibility = View.INVISIBLE
    }

    private fun UIshowError(){
        // Элементы при ошибке

        // VISIBLE
        binding.btnRetry.visibility = View.VISIBLE
        binding.ivBack.visibility = View.VISIBLE
        // INVISIBLE
        binding.pbMain.visibility = View.INVISIBLE
        binding.ivLike.visibility = View.INVISIBLE
        binding.ivLogo.visibility = View.INVISIBLE
        binding.tvName.visibility = View.INVISIBLE
        binding.tvId.visibility = View.INVISIBLE
        binding.tvCategory.visibility = View.INVISIBLE
        binding.tvTypeCoctail.visibility = View.INVISIBLE
        binding.tvTypeGlass.visibility = View.INVISIBLE
        binding.tvIngredients.visibility = View.INVISIBLE
    }

    private fun UIshowSuccess(){
        // Элементы при успешной загрузке

        // VISIBLE
        binding.ivLogo.visibility = View.VISIBLE
        binding.ivLike.visibility = View.VISIBLE
        binding.tvName.visibility = View.VISIBLE
        binding.tvId.visibility = View.VISIBLE
        binding.tvCategory.visibility = View.VISIBLE
        binding.tvTypeCoctail.visibility = View.VISIBLE
        binding.tvTypeGlass.visibility = View.VISIBLE
        binding.tvIngredients.visibility = View.VISIBLE
        binding.ivBack.visibility = View.VISIBLE
        // INVISIBLE
        binding.btnRetry.visibility = View.INVISIBLE
        binding.pbMain.visibility = View.INVISIBLE
    }

}