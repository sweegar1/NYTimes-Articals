package com.example.articals.data.repository

import com.example.articals.data.api.ApiHelper

class MainRepository(private val apiHelper: ApiHelper) {

suspend fun getArticalList() = apiHelper.getArticalList()

}