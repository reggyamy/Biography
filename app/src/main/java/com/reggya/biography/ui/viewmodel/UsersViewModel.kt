package com.reggya.biography.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.reggya.biography.data.model.UsersResponseItem
import com.reggya.biography.external.ApiResponse
import com.reggya.biography.domain.UsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersViewModel  @Inject constructor(private val usersUseCase: UsersUseCase) : ViewModel(){
	
	private val _users = MutableStateFlow<ApiResponse<List<UsersResponseItem?>>>(ApiResponse.Loading())
	val users : StateFlow<ApiResponse<List<UsersResponseItem?>>> = _users
	init {
		fetchUsers()
	}
	
	private fun fetchUsers() {
		viewModelScope.launch {
			usersUseCase.getUsers().collect{
				_users.value = it
			}
		}
	}
}