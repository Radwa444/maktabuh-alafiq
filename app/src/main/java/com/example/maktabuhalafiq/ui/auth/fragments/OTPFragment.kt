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
import com.example.maktabuhalafiq.ui.activity.UserMainActivity
import com.example.maktabuhalafiq.databinding.FragmentOtpBinding
import com.example.maktabuhalafiq.ui.auth.viewModels.SignupViewModel
import com.example.maktabuhalafiq.ui.common.views.ProgressDialog
import com.example.maktabuhalafiq.utils.UiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class OTPFragment : Fragment() {
    private lateinit var binding: FragmentOtpBinding
    private val viewModel: SignupViewModel by viewModels()
    val progressDialog by lazy {
        ProgressDialog.createProgressDialog(requireContext())
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOtpBinding.inflate(inflater, container, false)
        callBack()
        return binding.root
    }

    private fun callBack() {
        onClickBack()
        setupObservers()
        setupListeners()

    }

    private fun onClickBack() {
        binding.buttonBack.setOnClickListener{
            findNavController().navigateUp()
        }
    }
    private fun setupObservers() {
        lifecycleScope.launch {
            viewModel.otpState.collect { state ->
                when (state) {
                    is UiState.Loading -> {
                        progressDialog.show()
                    }
                    is UiState.Success -> {
                        if (state.data) {
                            progressDialog.dismiss()
                            startActivity(Intent(requireActivity(), UserMainActivity::class.java))
                            requireActivity().finish()
                        }
                    }
                    is UiState.Failure -> {
                        progressDialog.dismiss()
                        Log.d("OTP",state.error.toString())
                        Toast.makeText(
                            requireContext(),
                            state.error.toString(),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    else -> {}
                }
            }
        }
    }

    private fun setupListeners() {
        binding.buttonBack.setOnClickListener {
            findNavController().navigateUp()
        }

            viewModel.otp.value = binding.pinview.text.toString()
            viewModel.verifyOtp()

    }

}