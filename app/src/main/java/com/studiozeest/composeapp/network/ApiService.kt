package com.studiozeest.composeapp.network

import com.studiozeest.composeapp.model.Problems
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET

interface ApiService {

  @GET("2a7c0bc0-6430-4df5-8b78-7ac466b75595")
  suspend fun fetchDisneyPosterList(): ApiResponse<List<Problems>>
}
