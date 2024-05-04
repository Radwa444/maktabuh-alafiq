package com.example.maktabuhalafiq.ui.auth.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.maktabuhalafiq.R
import com.example.maktabuhalafiq.data.repository.user.UserPreferenceRepositoryImpl
import com.example.maktabuhalafiq.databinding.FragmentLoginBinding
import com.example.maktabuhalafiq.ui.auth.viewModels.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {
    val loginViewModel:LoginViewModel by lazy{
        LoginViewModel(userPrefs = UserPreferenceRepositoryImpl(requireActivity()))
    }
    private lateinit var binding: FragmentLoginBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentLoginBinding.inflate(layoutInflater)
        callBack()
        return binding.root
    }

    private fun callBack() {
        onClickText()
        onClickLogin()
        onClickForgetPassword()
    }

    private fun onClickForgetPassword() {
        binding.forget.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_resetPasswordFragment)
        }
    }

    private fun onClickLogin() {
        binding.LoginButton.setOnClickListener{

        }
    }


    private fun onClickText() {
        binding.textViewSign.setOnClickListener{
            findNavController().navigate(R.id.action_loginFragment_to_sigupFragment)
        }
    }

}