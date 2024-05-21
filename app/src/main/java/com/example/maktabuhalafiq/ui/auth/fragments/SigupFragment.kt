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
import com.example.maktabuhalafiq.databinding.FragmentSigupBinding
import com.example.maktabuhalafiq.ui.activity.UserMainActivity
import com.example.maktabuhalafiq.ui.auth.viewModels.LoginViewModel
import com.example.maktabuhalafiq.ui.auth.viewModels.SignupViewModel
import com.example.maktabuhalafiq.ui.common.views.ProgressDialog
import com.example.maktabuhalafiq.utils.UiState
import com.example.maktabuhalafiq.utils.Utils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SigupFragment : Fragment() {

    private lateinit var binding: FragmentSigupBinding
    private val viewModel: SignupViewModel by viewModels()
    val progressDialog by lazy {
        ProgressDialog.createProgressDialog(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSigupBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewmodel = viewModel
        onClickText()
        onClickSignup()
        initViewModel()

        return binding.root


    }

    private fun onClickText() {
        binding.textViewSigup.setOnClickListener{
            findNavController().navigate(R.id.action_sigupFragment_to_loginFragment)
        }
    }

    private fun initViewModel() {
        lifecycleScope.launch {
            viewModel.signupState.collect { statc ->
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


    private fun onClickSignup() {
        binding.SignupButton.setOnClickListener{

            viewModel.signup()

        }
    }
    companion object{
        private const val  TAG="FragmentSignup"
    }

}