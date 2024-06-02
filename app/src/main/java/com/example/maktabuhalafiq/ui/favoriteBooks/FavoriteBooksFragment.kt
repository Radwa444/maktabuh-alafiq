package com.example.maktabuhalafiq.ui.favoriteBooks

import Book
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.maktabuhalafiq.data.datasource.datastore.DataStoreManager
import com.example.maktabuhalafiq.databinding.FragmentFavoriteBooksBinding
import com.example.maktabuhalafiq.ui.Adapter.FavoriteBooksAdapter
import com.example.maktabuhalafiq.utils.UiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
@AndroidEntryPoint
class FavoriteBooksFragment : Fragment() {
    private lateinit var binding: FragmentFavoriteBooksBinding
    private lateinit var adapter: FavoriteBooksAdapter
    private val viewModel: FavoriteBooksViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBooksBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = FavoriteBooksAdapter(emptyList())
        binding.RFA.layoutManager = LinearLayoutManager(requireContext())
        binding.RFA.adapter = adapter

        observeFavoriteBooks()
        onClickBack()
    }

    private fun onClickBack() {
        binding.imageView22.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

    }

    private fun observeFavoriteBooks() {

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.favoriteBooks.observe(viewLifecycleOwner) { state ->
                when (state) {
                    is UiState.Success -> {
                        Log.e("FavoriteBooks",state.data.toString())
                        if (state.data.isEmpty()){
                            binding.RFA.visibility = View.GONE
                            binding.NoLike.visibility=View.VISIBLE
                            binding.NoLikeText.visibility=View.VISIBLE
                        }else{
                            binding.RFA.visibility = View.VISIBLE
                            binding.NoLike.visibility=View.GONE
                            binding.NoLikeText.visibility=View.GONE
                        }
                        adapter.updateBooks(state.data)
                    }
                    is UiState.Failure -> {
                        Log.e("FavoriteBooks",state.error.toString())
                    }
                    is UiState.Loading -> {
                        // Handle loading state
                    }
                }
            }
        }
    }
}