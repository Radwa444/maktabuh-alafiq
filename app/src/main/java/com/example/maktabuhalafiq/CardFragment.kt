package com.example.maktabuhalafiq

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.maktabuhalafiq.databinding.FragmentCardBinding
import com.example.maktabuhalafiq.databinding.FragmentOtpBinding
import com.example.maktabuhalafiq.databinding.FragmentSigupBinding
import com.example.maktabuhalafiq.databinding.FragmentStartBinding


class CardFragment : Fragment() {
    private lateinit var binding: FragmentCardBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =  FragmentCardBinding.inflate(inflater, container, false)

        return binding.root
    }




}