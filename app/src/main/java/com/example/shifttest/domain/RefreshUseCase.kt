package com.example.shifttest.domain

import com.example.shifttest.data.remote.toUser
import com.example.shifttest.data.remote.toUserEntity
import com.example.shifttest.data.repository.UserRepository
import javax.inject.Inject

class RefreshUseCase @Inject constructor(
    private val userRepository: UserRepository
){
    suspend fun execute(): List<User>{
        try{
            val result = userRepository.getUsers()
            if (result.isSuccessful){
                val results = result.body()!!.results
                userRepository.deleteAllUsers()
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