package com.example.articals.ui.adapters.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.articals.data.model.Results
import com.example.articals.data.repository.MainRepository
import com.example.articals.utils.Resource
import kotlinx.coroutines.Dispatchers

class MainViewModel (private val mainRepository: MainRepository, application: Application) :
 ViewModel() {
    var data = MutableLiveData<Results?>()

   fun getArticalList() = liveData(Dispatchers.IO) {

            emit(Resource.loading(data = null))
            try {
                emit(Resource.success(data = mainRepository.getArticalList()))
            } catch (e: Exception) {
                emit(Resource.error(data = null, message = e.message ?: "Exception Occured!!"))
            }

    }

    fun addArticalDetailsFragment(result: Results) {
        data.value = result
        data.postValue(null)
    }
}