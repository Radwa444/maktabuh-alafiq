package com.example.maktabuhalafiq

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.navigation.fragment.findNavController
import com.example.maktabuhalafiq.databinding.FragmentSigupBinding


class SigupFragment : Fragment() {
    private lateinit var binding: FragmentSigupBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSigupBinding.inflate(inflater, container, false)
        onClickText()
        onClickLogin()
        return binding.root


    }

    private fun onClickText() {
        binding.textViewSigup.setOnClickListener{
            findNavController().navigate(R.id.action_sigupFragment_to_loginFragment)
        }
    }
    private fun onClickLogin() {
        binding.LoginButton.setOnClickListener{
            findNavController().navigate(R.id.action_sigupFragment_to_OTPFragment)
        }
    }

}