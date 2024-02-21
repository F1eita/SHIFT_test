package com.example.shifttest.data.repository

import com.example.shifttest.data.remote.ResultsResponse
import com.example.shifttest.data.remote.UsersApi
import com.example.shifttest.data.storage.UserDao
import com.example.shifttest.data.storage.UserEntity
import retrofit2.Response
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val usersApi: UsersApi,
    private val usersDao: UserDao
) {

    suspend fun getUsers(): Response<ResultsResponse> {
        return usersApi.getUsers()
    }

    suspend fun getUsersFromDb(): List<UserEntity> {
        return usersDao.getUsers()
    }

    suspend fun addUsers(userEntities: List<UserEntity>){
        usersDao.addUsers(userEntities)
    }

    suspend fun deleteAllUsers(){
        usersDao.cleanTable()
    }

    suspend fun getUser(id: String): UserEntity{
        return usersDao.getUser(id)
    }

}