package app.nocamelstyle.cocktailguide.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.nocamelstyle.cocktailguide.databinding.ItemIngredientBinding
import app.nocamelstyle.cocktailguide.models.Ingredient


class InredientsAdapter(
    var ingredients: List<Ingredient>
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return Holder(ItemIngredientBinding.inflate(layoutInflater, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is Holder) holder.bind(position)
    }

    override fun getItemCount() = ingredients.size

    inner class Holder(private val view: ItemIngredientBinding): RecyclerView.ViewHolder(view.root) {
        @SuppressLint("SetTextI18n")
        fun bind(position: Int) {
            view.root.text = "${position + 1}. ${ingredients[position].strIngredient}"
        }
    }

}