package com.example.maktabuhalafiq.ui.auth.fragments

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.maktabuhalafiq.R
import com.example.maktabuhalafiq.data.repository.user.UserPreferenceRepositoryImpl
import com.example.maktabuhalafiq.databinding.FragmentSplashBinding
import com.example.maktabuhalafiq.ui.activity.UserMainActivity
import com.example.maktabuhalafiq.ui.auth.viewModels.UserViewModel
import com.example.maktabuhalafiq.ui.auth.viewModels.UserViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashFragment : Fragment() {
    private lateinit var binding: FragmentSplashBinding
    private val userViewModel:UserViewModel by viewModels {
        UserViewModelFactory(UserPreferenceRepositoryImpl(requireActivity()))
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentSplashBinding.inflate(layoutInflater)

        Handler(Looper.getMainLooper()).postDelayed({
            lifecycleScope.launch(Main) {
                val isLoggedIn=userViewModel.isUserLoggedIn().first()
                if(isLoggedIn){
                    findNavController().navigate(R.id.action_splashFragment_to_startFragment)
                }
                else{
                    startActivity(Intent(requireActivity(), UserMainActivity::class.java))
                    requireActivity().finish()

                }
            }
        },3000)
        return binding.root

    }


}