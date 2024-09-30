package com.reggya.biography.data

import android.content.ContentValues.TAG
import android.util.Log
import com.reggya.biography.external.ApiResponse
import com.reggya.biography.domain.IUsersRepository
import com.reggya.biography.data.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UsersRemoteDataSourceImpl  @Inject constructor(private val apiService: ApiService) :
	IUsersRepository {
	
	override fun getUsers() = flow {
			try {
				val response = apiService.getUsers()
				if (response.isNotEmpty()){
					Log.d(TAG, "getUsers: $response")
					emit(ApiResponse.Success(response))
				}else {
					Log.d(TAG, "getUsers: $response")
					emit(ApiResponse.Loading())
				}
			}catch (e: Exception){
				Log.e(TAG, "getUsers: ", e)
				emit(ApiResponse.Error(e.message.toString()))
			}
		}.flowOn(Dispatchers.IO)
	
}