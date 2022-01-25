package com.example.articals.data.api

import com.example.articals.data.model.ResponseArticalDetails
import com.example.articals.data.model.ResponseArticalList
import com.example.articals.utils.AppConstants
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("svc/mostpopular/v2/mostviewed/all-sections/7.json")
    suspend fun getArticalList(
        @Query(AppConstants.PARAM_API_KEY) apiKey: String,
    ): ResponseArticalList

}
