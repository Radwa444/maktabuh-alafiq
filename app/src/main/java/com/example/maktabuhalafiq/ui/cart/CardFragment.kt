package com.example.maktabuhalafiq.ui.cart

import Book
import android.annotation.SuppressLint
import android.os.Bundle

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.maktabuhalafiq.databinding.FragmentCardBinding
import com.example.maktabuhalafiq.ui.Adapter.CartAdapter
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class CardFragment : Fragment() {
    private lateinit var binding: FragmentCardBinding
    private lateinit var adapter: CartAdapter
    private val viewModel: CartViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()

        val userId = FirebaseAuth.getInstance().currentUser?.uid
        if (userId != null) {
            viewModel.fetchCartBooks(userId)
        }


        viewModel.cartBooks.observe(viewLifecycleOwner, Observer { books ->
            adapter.updateBooks(books)
        })
    }

    @SuppressLint("DefaultLocale")
    private fun setupRecyclerView() {
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        val quantityChangeListener: (Book, Int) -> Unit = { book, quantity ->
            if (userId != null) {
                viewModel.viewModelScope.launch {
                    viewModel.updateCartItemQuantity(userId, book.id, quantity)
                }
            }
        }

        adapter = CartAdapter(emptyList(), quantityChangeListener)
        binding.itemCart.layoutManager = LinearLayoutManager(requireContext())
        binding.itemCart.adapter = adapter

        viewModel.totalPrice.observe(viewLifecycleOwner, Observer { books ->
            binding.pirceSubtotal.text=books
        })
        viewModel.totalPriceSummation.observe(viewLifecycleOwner, Observer { books ->
            binding.pirceTotalSummation.text=books.toString()
        })
        binding.pirceDelivery.text="40"



    }
}