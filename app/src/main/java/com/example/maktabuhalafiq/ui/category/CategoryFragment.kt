package com.example.maktabuhalafiq.ui.category

import Book
import com.example.maktabuhalafiq.ui.Adapter.CategoryAdapter
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.maktabuhalafiq.R
import com.example.maktabuhalafiq.databinding.FragmentCategoryBinding
import com.example.maktabuhalafiq.ui.book.BooksFragment
import com.example.maktabuhalafiq.ui.common.views.ProgressDialog
import com.example.maktabuhalafiq.utils.SpaceItemCtagory
import com.example.maktabuhalafiq.utils.UiState
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CategoryFragment : Fragment() {
    private lateinit var binding: FragmentCategoryBinding
    private val viewModel: CategoryViewModel by viewModels()
    private lateinit var categoryAdapter: CategoryAdapter
    val progressDialog by lazy {
        ProgressDialog.createProgressDialog(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        categoryAdapter = CategoryAdapter { category ->
            try {
                navigateToBooks(category.books)

            }catch (e:Exception){
                Log.d("CategoryFragment",e.message.toString())
            }

            Log.d("CategoryFragment", category.books.toString())
        }

        binding.itemCategory.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = categoryAdapter
            addItemDecoration(SpaceItemCtagory(resources.getDimensionPixelSize(R.dimen.dimenButtonCategories)))
        }

        observeCategories()
        viewModel.fetchCategories()
    }

    private fun observeCategories() {
        viewModel.categories.observe(viewLifecycleOwner) { state ->
            when (state) {
                is UiState.Success -> {
                    progressDialog.dismiss()
                    val categories = state.data
                    categoryAdapter.submitList(categories)
                }
                is UiState.Failure -> {
                    progressDialog.dismiss()
                    Log.e("CategoryFragment", "Failed to fetch categories: ${state.error}")
                }
                is UiState.Loading ->{
                    progressDialog.show()
                }
            }
        }
    }

    private fun navigateToBooks(book:List<Book>) {
        try {
            val booksFragment = BooksFragment()

            val args = Bundle().apply {
                putParcelableArrayList("list_key", ArrayList(book))
                Log.e("args",book.toString())
            }
            booksFragment.arguments = args
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView2, booksFragment)
                .addToBackStack(null)
                .commit()

        } catch (e: Exception) {
            Log.d("ErrorNavCategory", e.message.toString())
        }
    }

}
