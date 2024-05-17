package com.example.maktabuhalafiq.ui.auth.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.maktabuhalafiq.R
import com.example.maktabuhalafiq.databinding.FragmentLoginBinding
import com.example.maktabuhalafiq.ui.activity.UserMainActivity
import com.example.maktabuhalafiq.ui.auth.viewModels.LoginViewModel
import com.example.maktabuhalafiq.ui.common.views.ProgressDialog
import com.example.maktabuhalafiq.ui.showSnakeBarError
import com.example.maktabuhalafiq.utils.UiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private val viewModel: LoginViewModel by viewModels()
    private lateinit var binding: FragmentLoginBinding
    val progressDialog by lazy {
        ProgressDialog.createProgressDialog(requireContext())
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(layoutInflater)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewmodel = viewModel
        callBack()
        return binding.root
    }

    private fun callBack() {
        onClickText()
        onClickLogin()
        onClickForgetPassword()
        initViewModel()
        binding.checkBox.setOnCheckedChangeListener { _, isChecked ->
            if (!isChecked) {
                view?.showSnakeBarError("Agree to the terms")
//                Toast.makeText(requireContext(), "add", Toast.LENGTH_SHORT).show()
            }
            viewModel.isCheckboxChecked.value = isChecked
        }
    }

    private fun initViewModel() {
        lifecycleScope.launch {
            viewModel.loginState.collect { statc ->
                Log.d("initViewModel", statc.toString())
                statc?.let { resources ->
                    when (resources) {
                        is UiState.Loading -> {
                            progressDialog.show()
                        }

                        is UiState.Success -> {
                            progressDialog.dismiss()
                            startActivity(Intent(requireActivity(), UserMainActivity::class.java))
                            requireActivity().finish()

                        }

                        is UiState.Failure -> {
                            progressDialog.dismiss()
                            Log.d(TAG, resources.error.toString())
                            Toast.makeText(
                                requireContext(),
                                resources.error.toString(),
                                Toast.LENGTH_SHORT
                            ).show()
                        }


                    }
                }


            }

        }
    }

    private fun onClickForgetPassword() {
        binding.forget.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_resetPasswordFragment)
        }
    }

    private fun onClickLogin() {
        binding.LoginButton.setOnClickListener {
            viewModel.login()

        }

    }


    private fun onClickText() {
        binding.textViewSign.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_sigupFragment)
        }
    }

    companion object {
        private const val TAG = "LoginFragment"
    }

}