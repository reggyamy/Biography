package com.reggya.biography.ui

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import com.reggya.biography.data.model.UsersResponseItem
import com.reggya.biography.databinding.FragmentUserDetailDialogBinding

private const val ARG_USER = "param1"

class UserDetailDialogFragment : DialogFragment() {
	private var user: UsersResponseItem? = null
	private lateinit var binding: FragmentUserDetailDialogBinding
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		arguments?.let {
			user = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
				it.getParcelable(ARG_USER, UsersResponseItem::class.java)
			} else it.getParcelable(ARG_USER)
		}
	}
	
	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		binding = FragmentUserDetailDialogBinding.inflate(layoutInflater)
		return binding.root
	}
	
	override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
		val dialog = super.onCreateDialog(savedInstanceState)
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
		return dialog
	}
	
	@SuppressLint("SetTextI18n")
	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		user?.firstName = user?.name?.split(" ")?.get(0) ?: ""
		user?.lastName = user?.name?.replace("${user?.firstName} ", "")
		binding.user = user
		Glide.with(this.requireContext())
			.load(user?.avatar)
			.into(binding.image)
		binding.address.text = "St. ${user?.street} No. ${user?.addressNo}, ${user?.county}, ${user?.city}, ${user?.country}, ${user?.zipCode}"
		binding.btnClose.setOnClickListener {
			dismiss()
		}
	}
	
	override fun dismiss(){
		parentFragmentManager.beginTransaction()
			.remove(this)
			.commit()
	}
	
	companion object {
		@JvmStatic
		fun newInstance(usersResponseItem: UsersResponseItem) =
			UserDetailDialogFragment().apply {
				arguments = Bundle().apply {
					putParcelable(ARG_USER, usersResponseItem)
				}
			}
	}
}