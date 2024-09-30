package com.reggya.biography.domain

import com.reggya.biography.data.UsersRemoteDataSourceImpl
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UsersUseCase @Inject constructor(private val usersRemoteDataSourceImpl: UsersRemoteDataSourceImpl){
	
	fun getUsers() = usersRemoteDataSourceImpl.getUsers()
}