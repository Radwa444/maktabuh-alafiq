package com.example.maktabuhalafiq.ui.archive

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.maktabuhalafiq.databinding.FragmentArchivesBinding
import com.example.maktabuhalafiq.databinding.FragmentFavoriteBooksBinding
import com.example.maktabuhalafiq.databinding.FragmentLoginBinding
import com.example.maktabuhalafiq.databinding.FragmentSplashBinding
import com.example.maktabuhalafiq.ui.Adapter.BooksArchivesAdapter
import com.example.maktabuhalafiq.ui.Adapter.FavoriteBooksAdapter
import com.example.maktabuhalafiq.ui.favoriteBooks.FavoriteBooksViewModel
import com.example.maktabuhalafiq.utils.UiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ArchivesFragment : Fragment() {
    private lateinit var binding: FragmentArchivesBinding
    private lateinit var adapter: BooksArchivesAdapter
    private val viewModel: ArchivesViewModel by viewModels()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentArchivesBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = BooksArchivesAdapter(emptyList())
        binding.RAR.layoutManager = LinearLayoutManager(requireContext())
        binding.RAR.adapter = adapter

        observeFavoriteBooks()
        onClickBack()
    }

    private fun onClickBack() {

    }

    private fun observeFavoriteBooks() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.archivesBooks.observe(viewLifecycleOwner) { state ->
                when (state) {
                    is UiState.Success -> {
                        Log.e("archivesBooks",state.data.toString())
                        if (state.data.isEmpty()){
                            binding.RAR.visibility = View.GONE
                            binding.textView17.visibility=View.VISIBLE
                            binding.imageView10.visibility=View.VISIBLE
                            binding.guideline2.visibility=View.VISIBLE
                        }else{
                            binding.RAR.visibility = View.VISIBLE
                            binding.textView17.visibility=View.GONE
                            binding.imageView10.visibility=View.GONE
                            binding.guideline2.visibility=View.GONE

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