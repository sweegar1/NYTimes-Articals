package com.example.articals.data.api

import com.example.articals.utils.AppConstants

class ApiHelper(private val apiService: ApiService) {

    suspend fun getArticalList() = apiService.getArticalList(
        AppConstants.API_KEY
    )

}