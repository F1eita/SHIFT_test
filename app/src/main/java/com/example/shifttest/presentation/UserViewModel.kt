package com.example.shifttest.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shifttest.data.remote.ResponseStates
import com.example.shifttest.domain.GetUsersUseCase
import com.example.shifttest.domain.User
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserViewModel @Inject constructor(
    private val getUserUseCase: GetUsersUseCase,
) : ViewModel() {

    private val _userLiveData = MutableLiveData<ResponseStates<List<User>>>()
    val userLiveData: LiveData<ResponseStates<List<User>>> = _userLiveData

    fun getUsers() {
        viewModelScope.launch {
            _userLiveData.value = ResponseStates.Loading()
            try {
                Log.d("proverkaaa", "success")
                _userLiveData.value = ResponseStates.Success(
                    getUserUseCase.execute()
                )
            } catch (e: Exception) {
                Log.d("proverkaaa", "fail")
                _userLiveData.value = ResponseStates.Failure(
                    e
                )
            }
        }
    }
}