package com.bychinin.androidtesttask.UI.adapetrs

import android.content.Context
import android.text.method.TextKeyListener.clear
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bychinin.androidtesttask.R
import com.bychinin.androidtesttask.data.classes.ShortDrink
import com.bychinin.androidtesttask.data.objects.ViewCoctails
import com.bychinin.androidtesttask.databinding.LayoutViewShortBinding
import com.squareup.picasso.Picasso
import java.util.Collections.addAll

class ViewAdapter(val list : MutableList<ShortDrink>, private val context: Context) : RecyclerView.Adapter<ViewAdapter.ViewAdapterDataHolder>(){

    // Адаптер для списка просмотренных коктейлей

    private lateinit var binding : LayoutViewShortBinding

    init {
        binding = LayoutViewShortBinding.inflate(LayoutInflater.from(context))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewAdapterDataHolder =
            ViewAdapterDataHolder(LayoutViewShortBinding.inflate(LayoutInflater.from(context), parent, false))

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewAdapterDataHolder, position: Int) {
        Picasso
            .get()
            .load(list[position].logo)
            .placeholder(R.color.purple_500)
            .into(holder.itemBinding.ivShortLogo)
        holder.itemBinding.tvShortName.text = list[position].name
    }

    class ViewAdapterDataHolder(val itemBinding: LayoutViewShortBinding) : RecyclerView.ViewHolder(itemBinding.root){

        private var binding : LayoutViewShortBinding? = null

        init {
            this.binding = itemBinding
        }

    }

}

