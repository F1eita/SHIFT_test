package com.example.shifttest.data.repository

import com.example.shifttest.data.remote.ResultsResponse
import com.example.shifttest.data.remote.UsersApi
import com.example.shifttest.domain.UserRepository
import retrofit2.Response
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val usersApi: UsersApi
): UserRepository {

    override suspend fun getUsers(): Response<ResultsResponse> {
        return usersApi.getUsers()
    }

}