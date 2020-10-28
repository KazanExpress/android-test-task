package app.nocamelstyle.cocktailguide.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import app.nocamelstyle.cocktailguide.R
import app.nocamelstyle.cocktailguide.databinding.ActivityHomeBinding
import app.nocamelstyle.cocktailguide.fragments.CategoriesFragment
import app.nocamelstyle.cocktailguide.fragments.FavouritesFragment
import app.nocamelstyle.cocktailguide.fragments.SearchFragment
import app.nocamelstyle.cocktailguide.models.Drink
import app.nocamelstyle.cocktailguide.utils.startActivity
import com.google.gson.Gson

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.navigetionView.setOnNavigationItemSelectedListener {
            supportFragmentManager.commit {
                replace(
                    R.id.frame,
                    when (it.itemId) {
                        R.id.bottom_menu_search -> SearchFragment()
                        R.id.bottom_menu_categories -> CategoriesFragment()
                        else -> FavouritesFragment()
                    }
                )
            }
            true
        }

    }

}