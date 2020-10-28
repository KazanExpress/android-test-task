package app.nocamelstyle.cocktailguide.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import app.nocamelstyle.cocktailguide.R
import app.nocamelstyle.cocktailguide.activities.CocktailActivity
import app.nocamelstyle.cocktailguide.adapters.HistoryVisitAdapter
import app.nocamelstyle.cocktailguide.databinding.FragmentSearchBinding
import app.nocamelstyle.cocktailguide.models.AnswerDrinks
import app.nocamelstyle.cocktailguide.models.Drink
import app.nocamelstyle.cocktailguide.models.DrinkRealm
import app.nocamelstyle.cocktailguide.services.ApiService
import app.nocamelstyle.cocktailguide.utils.onRightDrawableClicked
import app.nocamelstyle.cocktailguide.utils.startActivity
import app.nocamelstyle.cocktailguide.utils.toast
import com.google.gson.Gson
import io.realm.Realm
import io.realm.kotlin.where
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Response

class SearchFragment : Fragment(R.layout.fragment_search) {

    private var binding: FragmentSearchBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            randomButton.setOnClickListener {
                refreshLayout.isRefreshing = true
                GlobalScope.launch(Dispatchers.IO) {
                    try {
                        onDrinksLoaded(ApiService.loadRandomDrink().body()?.drinks)
                    } catch (e: Exception) {
                        onDrinksLoaded(null)
                    }
                    launch(Dispatchers.Main) {
                        refreshLayout.isRefreshing = false
                    }
                }
            }

            refreshLayout.isEnabled = false

            searchButton.setOnClickListener {
                refreshLayout.isRefreshing = true
                GlobalScope.launch(Dispatchers.IO) {
                    try {
                        onDrinksLoaded(
                            ApiService.searchDrinks(filter.text.toString()).body()?.drinks
                        )
                    } catch (e: Exception) {
                        onDrinksLoaded(null)
                    }
                    launch(Dispatchers.Main) {
                        refreshLayout.isRefreshing = false
                    }
                }
            }

            filter.doOnTextChanged { _, _, _, count ->
                if (count != 0) {
                    val cancel = ContextCompat.getDrawable(requireContext(), R.drawable.ic_baseline_close_24)
                    cancel?.setBounds(0,0, cancel.intrinsicWidth, cancel.intrinsicHeight)
                    filter.setCompoundDrawables(null, null, cancel, null)
                } else {
                    filter.setCompoundDrawables(null, null, null, null)
                }
            }

            filter.onRightDrawableClicked { it.text.clear() }
        }
    }

    private fun onDrinksLoaded(drinks: List<Drink>?) {
        GlobalScope.launch(Dispatchers.Main) {
            if (drinks == null)
                toast(R.string.internet_error)
            else if (drinks.isNotEmpty()) {
                startActivity<CocktailActivity> {
                    putExtra("drink", Gson().toJson(drinks.first()))
                }
            } else
                toast(R.string.empty_list)
        }
    }

    override fun onResume() {
        super.onResume()

        GlobalScope.launch(Dispatchers.Main) {
            val drinks = Realm.getDefaultInstance()
                    .where<DrinkRealm>()
                    .findAll()
                    .map { it.toBasicVersion() }
                    .filter { it.idDrink != null }
                    .reversed()

            binding?.historyList?.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = HistoryVisitAdapter(requireContext(), drinks)
            }
        }
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }

}