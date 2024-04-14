package com.example.maktabuhalafiq

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.example.maktabuhalafiq.databinding.FragmentSigupBinding
import com.example.maktabuhalafiq.databinding.FragmentStartBinding


class StartFragment : Fragment() {
    private lateinit var binding:FragmentStartBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,

        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentStartBinding.inflate(layoutInflater)

        callBack()

        return binding.root
    }

    private fun callBack() {
        inClickButtonSign()
        inClickButtonLogin()
    }

    private fun inClickButtonSign() {
        binding.buttonSign.setOnClickListener{
            findNavController().navigate(R.id.action_startFragment_to_sigupFragment)
        }
    }
    private fun inClickButtonLogin() {
        binding.buttonLogin.setOnClickListener{
            findNavController().navigate(R.id.action_startFragment_to_loginFragment)
        }
    }

}