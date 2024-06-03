package com.example.maktabuhalafiq.ui.download

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.maktabuhalafiq.databinding.FragmentDownloadBinding
import com.example.maktabuhalafiq.ui.Adapter.DoneDownloadAdapter
import com.example.maktabuhalafiq.utils.UiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
@AndroidEntryPoint
class DownloadFragment : Fragment() {
    private lateinit var binding: FragmentDownloadBinding
    private lateinit var adapter: DoneDownloadAdapter
    private val viewModel: DownloadViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDownloadBinding.inflate(inflater, container, false)
        adapter = DoneDownloadAdapter(emptyList())
        binding.RDB.adapter = adapter
        binding.RDB.layoutManager = LinearLayoutManager(context) // Set LayoutManager
        onClickBack()
        observeFavoriteBooks()
        return binding.root // Return the root view of the binding
    }

    private fun observeFavoriteBooks() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.downloadBooks.observe(viewLifecycleOwner) { state ->
                when (state) {
                    is UiState.Success -> {
                        Log.e("FavoriteBooks", state.data.toString())
                        if (state.data.isEmpty()) {
                            binding.RDB.visibility = View.GONE
                            binding.imageView24.visibility = View.VISIBLE
                            binding.textView59.visibility = View.VISIBLE
                        } else {
                            binding.RDB.visibility = View.VISIBLE
                            binding.imageView24.visibility = View.GONE
                            binding.textView61.visibility = View.GONE
                        }
                        adapter.updateBooks(state.data)
                    }
                    is UiState.Failure -> {
                        Log.e("FavoriteBooks", state.error.toString())
                    }
                    is UiState.Loading -> {
                        // Handle loading state
                    }
                }
            }
        }
    }
    private fun onClickBack() {
        binding.onClickBackDownload.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

    }
}
