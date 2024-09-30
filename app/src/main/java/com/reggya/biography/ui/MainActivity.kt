package com.reggya.biography.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.reggya.biography.R
import com.reggya.biography.external.UsersAdapter
import com.reggya.biography.data.model.UsersResponseItem
import com.reggya.biography.ui.viewmodel.UsersViewModel
import com.reggya.biography.external.ApiResponse
import com.reggya.biography.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
	private lateinit var binding: ActivityMainBinding
	private val viewModel: UsersViewModel by viewModels()
	private val usersAdapter : UsersAdapter by lazy {
		UsersAdapter()
	}
	private lateinit var dialogFragment : UserDetailDialogFragment
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		binding = ActivityMainBinding.inflate(layoutInflater)
		setContentView(binding.root)
		ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
			val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
			v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
			insets
		}
		
		binding.rvUsers.layoutManager = LinearLayoutManager(this)
		binding.rvUsers.adapter = usersAdapter
		
		lifecycleScope.launch {
			repeatOnLifecycle(Lifecycle.State.STARTED){
				viewModel.users.collect{
					when(it){
						is ApiResponse.Error -> {
							binding.progressCircular.isVisible = true
							Toast.makeText(this@MainActivity, "${it.message}", Toast.LENGTH_SHORT).show()
						}
						is ApiResponse.Loading -> {
							binding.progressCircular.isVisible = true
							Toast.makeText(this@MainActivity, "Load the data", Toast.LENGTH_SHORT).show()
						}
						is ApiResponse.Success -> {
							binding.progressCircular.isVisible = false
							setUpData(it.data)
						}
					}
				}
			}
		}
		usersAdapter.itemOnClickListener = {
			showDialog(it)
		}
	}
	
	private fun showDialog(usersResponseItem: UsersResponseItem) {
		val isLargeLayout = resources.getBoolean(R.bool.large_layout)
		val fragmentManager = supportFragmentManager
		val transaction = fragmentManager.beginTransaction()
		dialogFragment = UserDetailDialogFragment.newInstance(usersResponseItem)
		if (isLargeLayout) {
			dialogFragment.show(fragmentManager, "dialog")
		} else {
			transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
			transaction
				.add(android.R.id.content, dialogFragment)
				.addToBackStack(null)
				.commit()
		}
	}
	
	private fun setUpData(data: List<UsersResponseItem?>?) {
		usersAdapter.updateData(data)
	}
}