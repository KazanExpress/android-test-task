package com.bychinin.androidtesttask.UI.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.bychinin.androidtesttask.data.repositories.RandomRepository
import com.bychinin.androidtesttask.utils.Resource
import kotlinx.coroutines.Dispatchers

class RandomViewModel(private val randomRepository: RandomRepository) : ViewModel() {

    fun getRandomCoctail() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = randomRepository.getRandomCoctail()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    fun getSearchCoctail(name : String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = randomRepository.getSearchCoctail(name)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

}