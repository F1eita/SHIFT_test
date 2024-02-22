package com.example.shifttest.domain

import com.example.shifttest.data.repository.UserRepository
import com.example.shifttest.data.storage.toUserInfo
import javax.inject.Inject

class GetUserByIdUseCase @Inject constructor(
    private val userRepository: UserRepository
){
    suspend fun execute(id: String): UserInfo{
        return userRepository.getUser(id).toUserInfo()
    }

}