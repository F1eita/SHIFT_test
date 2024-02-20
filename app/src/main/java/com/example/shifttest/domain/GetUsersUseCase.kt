package com.example.shifttest.domain

import android.util.Log
import com.example.shifttest.data.remote.toUser
import com.example.shifttest.data.repository.UserRepository
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
    private val userRepository: UserRepository
){
    suspend fun execute(): List<User>{
        try{
            val result = userRepository.getUsers()
            if (result.isSuccessful){
                Log.d("proverka", "good")
                return result.body()!!.results.map { it.toUser() }
            } else {
                Log.d("proverka", "error1")
                throw Exception(result.errorBody()!!.string())
            }
        } catch (e: Exception){
            Log.d("proverka", e.message.toString())
            throw Exception(e.message)
        }
    }

}