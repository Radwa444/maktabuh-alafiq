package com.example.maktabuhalafiq

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.maktabuhalafiq.databinding.FragmentAuthorBinding
import com.example.maktabuhalafiq.databinding.FragmentSplashBinding


class AuthorFragment : Fragment() {
    private lateinit var binding: FragmentAuthorBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentAuthorBinding.inflate(layoutInflater)

        return binding.root

    }


}