package com.example.shifttest.data.repository

import com.example.shifttest.data.remote.ResultsResponse
import com.example.shifttest.data.remote.UsersApi
import retrofit2.Response
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val usersApi: UsersApi
) {

    suspend fun getUsers(): Response<ResultsResponse> {
        return usersApi.getUsers()
    }

}