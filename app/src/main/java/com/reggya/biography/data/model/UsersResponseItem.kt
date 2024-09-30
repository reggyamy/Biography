package com.reggya.biography.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class UsersResponseItem(

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("country")
	val country: String? = null,

	@field:SerializedName("address_no")
	val addressNo: String? = null,

	@field:SerializedName("city")
	val city: String? = null,

	@field:SerializedName("street")
	val street: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("county")
	val county: String? = null,

	@field:SerializedName("avatar")
	val avatar: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("zip_code")
	val zipCode: String? = null,
	var firstName : String,
	var lastName : String? = null
) : Parcelable