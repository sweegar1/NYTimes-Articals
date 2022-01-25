package com.example.articals.ui.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.articals.data.api.ApiHelper
import com.example.articals.data.repository.MainRepository

class ArticalDetailsViewModelFactory  (private val apiHelper: ApiHelper, private val context: Context):
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ArticalDetailsViewModel::class.java)) {
            return ArticalDetailsViewModel(MainRepository(apiHelper), context as Application) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}