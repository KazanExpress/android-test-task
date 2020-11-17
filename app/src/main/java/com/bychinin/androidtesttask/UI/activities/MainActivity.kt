package com.bychinin.androidtesttask.UI.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bychinin.androidtesttask.UI.adapetrs.ViewAdapter
import com.bychinin.androidtesttask.data.objects.ViewCoctails.viewCocktails
import com.bychinin.androidtesttask.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var viewAdapter : ViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Создание RecycleView
        binding.rvList.layoutManager = LinearLayoutManager(this)
        viewAdapter = ViewAdapter(viewCocktails, this)
        binding.rvList.adapter = viewAdapter

        // Поиск коктейля
        binding.btnFind.setOnClickListener {
            val intent = Intent(this, RandomActivity::class.java)
            intent.putExtra("name", binding.etFind.text.toString())
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        // Обновление списка
        viewAdapter = ViewAdapter(viewCocktails, this)
        binding.rvList.adapter = viewAdapter
        viewAdapter.notifyDataSetChanged()
    }

}