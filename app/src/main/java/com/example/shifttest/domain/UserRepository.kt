package com.example.shifttest.domain

import com.example.shifttest.data.remote.ResultsResponse
import retrofit2.Response

interface UserRepository {

    suspend fun getUsers(): Response<ResultsResponse>

}