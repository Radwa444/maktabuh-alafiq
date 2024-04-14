package com.example.maktabuhalafiq

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.maktabuhalafiq.databinding.FragmentArchivesBinding
import com.example.maktabuhalafiq.databinding.FragmentLoginBinding
import com.example.maktabuhalafiq.databinding.FragmentSplashBinding


class ArchivesFragment : Fragment() {
    private lateinit var binding: FragmentArchivesBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentArchivesBinding.inflate(layoutInflater)

        return binding.root
    }



}