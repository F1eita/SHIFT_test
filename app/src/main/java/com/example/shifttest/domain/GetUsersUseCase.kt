package com.example.shifttest.domain

import com.example.shifttest.data.remote.toUser
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
    private val userRepository: UserRepository
){
    suspend fun execute(): List<User>{
        try{
            val result = userRepository.getUsers()
            if (result.isSuccessful){
                return result.body()!!.results.map { it.toUser() }
            } else {
                throw Exception(result.errorBody()!!.string())
            }
        } catch (e: Exception){
            throw Exception(e.message)
        }
    }

}