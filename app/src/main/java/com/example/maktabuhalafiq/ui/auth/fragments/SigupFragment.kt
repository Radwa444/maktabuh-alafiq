package com.example.maktabuhalafiq.ui.auth.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.maktabuhalafiq.R
import com.example.maktabuhalafiq.databinding.FragmentSigupBinding
import com.example.maktabuhalafiq.utils.Utils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SigupFragment : Fragment() {
    val TAG="FragmentSignup"
    private lateinit var binding: FragmentSigupBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSigupBinding.inflate(inflater, container, false)
        onClickText()
        onClickSignup()

        return binding.root


    }

    private fun onClickText() {
        binding.textViewSigup.setOnClickListener{
            findNavController().navigate(R.id.action_sigupFragment_to_loginFragment)
        }
    }

    private fun validation(): Boolean {
        var isValid = true

        if (binding.editTextName.text.isNullOrEmpty()) {
            isValid = false
            Utils().showToast(requireContext(), getString(R.string.enter_name))
        }

        if (binding.editTextEmail.text.isNullOrEmpty()) {
            isValid = false
            Utils().showToast(requireContext(), "أدخل البريد الإلكتروني")
        } else if (!Utils().isValidEmail(binding.editTextEmail.text.toString())) {
            isValid = false
            Utils().showToast(requireContext(), "البريد الإلكتروني غير صالح")
        }

        if (binding.editTextPassword.text.isNullOrEmpty()) {
            isValid = false
            Utils().showToast(requireContext(), "أدخل كلمة المرور")
        }

        if (binding.editTextConfirmPassword.text.isNullOrEmpty()) {
            isValid = false
            Utils().showToast(requireContext(), "أدخل تأكيد كلمة المرور")
        }

        return isValid
    }




    private fun onClickSignup() {
        binding.LoginButton.setOnClickListener{
            if(validation()){
                findNavController().navigate(R.id.action_sigupFragment_to_OTPFragment)
            }
            else{

            }
        }
    }

}