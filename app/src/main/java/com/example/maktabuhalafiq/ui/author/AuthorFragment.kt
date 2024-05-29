package com.example.maktabuhalafiq.ui.author
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.maktabuhalafiq.databinding.FragmentAuthorBinding
import com.example.maktabuhalafiq.ui.Adapter.AuthorAdapter
import com.example.maktabuhalafiq.ui.common.views.ProgressDialog
import com.example.maktabuhalafiq.utils.UiState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthorFragment : Fragment() {
    private lateinit var binding: FragmentAuthorBinding
    private val viewModel: AuthorViewModel by viewModels()
    private lateinit var authorAdapter: AuthorAdapter
    val progressDialog by lazy {
        ProgressDialog.createProgressDialog(requireContext())
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAuthorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        authorAdapter = AuthorAdapter { author ->
            try {
                //navigateToBooks(author.books)
            } catch (e: Exception) {
                Log.d("AuthorFragment", e.message.toString())
            }
            Log.d("AuthorFragment", author.books.toString())
        }

        binding.itemAuthor.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = authorAdapter
            // addItemDecoration(SpaceItemDecoration(resources.getDimensionPixelSize(R.dimen.dimenButtonCategories)))
        }

        observeAuthors()
        viewModel.fetchAuthors()
    }

    private fun observeAuthors() {
        viewModel.author.observe(viewLifecycleOwner) { state ->
            when (state) {
                is UiState.Success -> {
                    progressDialog.dismiss()
                    authorAdapter.submitList(state.data)
                }
                is UiState.Failure -> {
                    progressDialog.dismiss()
                    Log.e("AuthorFragment", "Failed to fetch authors: ${state.error}")
                }
                is UiState.Loading ->{
                    progressDialog.show()
                }
            }
        }
    }



}