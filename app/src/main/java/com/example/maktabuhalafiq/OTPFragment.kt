package com.example.maktabuhalafiq

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.maktabuhalafiq.databinding.FragmentOtpBinding
import com.example.maktabuhalafiq.databinding.FragmentSigupBinding
import com.example.maktabuhalafiq.databinding.FragmentStartBinding


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
    }

    private fun onClickBack() {
        binding.buttonBack.setOnClickListener{
            findNavController().navigateUp()
        }
    }


}