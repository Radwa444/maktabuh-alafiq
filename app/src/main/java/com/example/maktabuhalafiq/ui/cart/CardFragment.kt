package com.example.maktabuhalafiq.ui.cart

import Book
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.maktabuhalafiq.databinding.FragmentCardBinding
import com.example.maktabuhalafiq.databinding.FragmentOtpBinding
import com.example.maktabuhalafiq.databinding.FragmentSigupBinding
import com.example.maktabuhalafiq.databinding.FragmentStartBinding
import com.example.maktabuhalafiq.ui.Adapter.BookAdapter
import com.example.maktabuhalafiq.ui.Adapter.CartAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CardFragment : Fragment() {
    private lateinit var binding: FragmentCardBinding
    private lateinit var adapter : CartAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =  FragmentCardBinding.inflate(inflater, container, false)
        try {
//            val list = arguments?.getParcelableArrayList<Book>("add_cart") ?: listOf()
//            Log.e("cart",list.toString())
//            adapter = CartAdapter(list)
//            binding.itemCart.adapter = adapter
        }catch (e:Exception){
            Log.e("cart",e.toString())
        }



        return binding.root
    }




}