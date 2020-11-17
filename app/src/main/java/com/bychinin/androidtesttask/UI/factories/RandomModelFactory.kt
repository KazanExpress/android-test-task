package com.bychinin.androidtesttask.UI.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bychinin.androidtesttask.UI.models.RandomViewModel
import com.bychinin.androidtesttask.data.api.ApiHelper
import com.bychinin.androidtesttask.data.repositories.RandomRepository

class RandomModelFactory(private val ApiHelper: ApiHelper) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RandomViewModel::class.java)) {
            return RandomViewModel(RandomRepository(ApiHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}