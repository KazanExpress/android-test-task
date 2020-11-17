package com.bychinin.androidtesttask.UI.adapetrs

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bychinin.androidtesttask.R
import com.bychinin.androidtesttask.databinding.SlidesPagesBinding
import java.lang.ref.WeakReference

class SlidesAdapter(private val context: Context) : RecyclerView.Adapter<DataViewHolder>(){

    // Адаптер для viewpager

    private lateinit var binding : SlidesPagesBinding

    init {
        binding = SlidesPagesBinding.inflate(LayoutInflater.from(context))
    }

    private val colors = arrayOf(R.drawable.slide1, R.drawable.slide2, R.drawable.slide3)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        DataViewHolder(SlidesPagesBinding.inflate(LayoutInflater.from(context), parent, false))

    override fun getItemCount(): Int = colors.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.itemBinding.ivLogo.setImageResource(colors[position])
    }
}

class DataViewHolder(val itemBinding: SlidesPagesBinding) : RecyclerView.ViewHolder(itemBinding.root){

    private var binding : SlidesPagesBinding? = null

    init {
        this.binding = itemBinding
    }

}