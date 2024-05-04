package com.example.maktabuhalafiq.ui.auth.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.maktabuhalafiq.ui.activity.UserMainActivity
import com.example.maktabuhalafiq.databinding.FragmentOtpBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OTPFragment : Fragment() {
    private lateinit var binding: FragmentOtpBinding
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
        startActivity(Intent(requireActivity(), UserMainActivity::class.java))
        requireActivity().finish()
    }

    private fun onClickBack() {
        binding.buttonBack.setOnClickListener{
            findNavController().navigateUp()
        }
    }


}