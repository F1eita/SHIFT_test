package com.example.shifttest.domain

import android.util.Log
import com.example.shifttest.data.remote.toUser
import com.example.shifttest.data.remote.toUserEntity
import com.example.shifttest.data.repository.UserRepository
import com.example.shifttest.data.storage.toUser
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
    private val userRepository: UserRepository
){
    suspend fun execute(): List<User>{
        try{
            val users = userRepository.getUsersFromDb()
            if (users.isNotEmpty())
                return users.map { it.toUser() }
            val result = userRepository.getUsers()
            if (result.isSuccessful){
                val results = result.body()!!.results
                userRepository.addUsers(results.map{it.toUserEntity()})
                return results.map { it.toUser() }
            } else {
                throw Exception(result.errorBody()!!.string())
            }
        } catch (e: Exception){
            throw Exception(e.message)
        }
    }

}