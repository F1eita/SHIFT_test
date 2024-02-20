package com.example.shifttest.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface UsersApi {

    @GET(".")
    suspend fun getUsers(
        @Query("page") page: Int = 1,
        @Query("results") results: Int = 20
    ): Response<ResultsResponse>

}