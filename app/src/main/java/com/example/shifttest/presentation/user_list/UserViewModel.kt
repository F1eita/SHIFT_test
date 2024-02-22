package com.example.shifttest.presentation.user_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shifttest.data.remote.ResponseStates
import com.example.shifttest.domain.GetUsersUseCase
import com.example.shifttest.domain.RefreshUseCase
import com.example.shifttest.domain.User
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserViewModel @Inject constructor(
    private val getUserUseCase: GetUsersUseCase,
    private val refreshUseCase: RefreshUseCase
) : ViewModel() {

    private val _userLiveData = MutableLiveData<ResponseStates<List<User>>>()
    val userLiveData: LiveData<ResponseStates<List<User>>> = _userLiveData

    fun getUsers() {
        viewModelScope.launch {
            _userLiveData.value = ResponseStates.Loading()
            try {
                _userLiveData.value = ResponseStates.Success(
                    getUserUseCase.execute()
                )
            } catch (e: Exception) {
                _userLiveData.value = ResponseStates.Failure(
                    e
                )
            }
        }
    }

    fun refreshUsers(){
        viewModelScope.launch {
            _userLiveData.value = ResponseStates.Loading()
            try {
                _userLiveData.value = ResponseStates.Success(
                    refreshUseCase.execute()
                )
            } catch (e: Exception) {
                _userLiveData.value = ResponseStates.Failure(
                    e
                )
            }
        }
    }
}