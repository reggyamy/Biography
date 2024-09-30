package com.reggya.biography.domain

import com.reggya.biography.data.model.UsersResponseItem
import com.reggya.biography.external.ApiResponse
import kotlinx.coroutines.flow.Flow

interface IUsersRepository {
	
	fun getUsers() : Flow<ApiResponse<List<UsersResponseItem?>>>
	
}
