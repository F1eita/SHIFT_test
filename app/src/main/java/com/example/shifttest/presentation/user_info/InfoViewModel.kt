package com.example.shifttest.presentation.user_info

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shifttest.domain.GetUserByIdUseCase
import com.example.shifttest.domain.UserInfo
import kotlinx.coroutines.launch
import javax.inject.Inject

class InfoViewModel@Inject constructor(
    private val getUserByIdUseCase: GetUserByIdUseCase
) : ViewModel() {

    private val _userLiveData = MutableLiveData<UserInfo>()
    val userLiveData: LiveData<UserInfo> = _userLiveData

    fun getUser(id: String) {
        viewModelScope.launch {
            _userLiveData.value = getUserByIdUseCase.execute(id)
        }
    }
}