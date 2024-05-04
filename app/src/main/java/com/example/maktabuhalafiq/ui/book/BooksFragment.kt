package com.example.maktabuhalafiq.ui.book

import Book
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.maktabuhalafiq.DeailsBookFragment
import com.example.maktabuhalafiq.R

import com.example.maktabuhalafiq.databinding.FragmentBooksBinding
import com.example.maktabuhalafiq.ui.Adapter.BookAdapter
import com.example.maktabuhalafiq.ui.category.CategoryFragment
import com.example.maktabuhalafiq.utils.SpaceItemCtagory
import com.example.maktabuhalafiq.utils.UiState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BooksFragment : Fragment() {
    private lateinit var binding: FragmentBooksBinding
    private lateinit var adapter : BookAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentBooksBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = BookAdapter(emptyList()) { selectedBook ->

            navigateToBooks(selectedBook)
        }

        binding.listBook.adapter = adapter
        binding.listBook.addItemDecoration(SpaceItemCtagory(resources.getDimensionPixelSize(R.dimen.dimenButtonCategories)))
        binding.ButtonBack.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView2, CategoryFragment())
                .addToBackStack(null)
                .commit()
        }

        try {
            val bookList = arguments?.getParcelableArrayList<Book>("list_key")
            val list = arguments?.getParcelableArrayList<Book>("list")
            Log.d("BooksFragment", "Received book list: ${bookList?.size}")
            list?.let {
                adapter.updateBooks(it)
                Log.d("BooksFragment", "Received book list: $it")
            } ?: Log.e("BooksFragment", "No book list received")
            bookList?.let {
                adapter.updateBooks(it)
                Log.d("BooksFragment", "Received book list: $it")
            } ?: Log.e("BooksFragment", "No book list received")
        } catch (e: Exception) {
            Log.e("BooksFragment", e.message.toString())
        }



    }
    private fun navigateToBooks(book:Book) {
        try {
            val deailsBooksFragment = DeailsBookFragment()

            val args = Bundle().apply {
                putParcelable("book_key", book)
                Log.e("args", book.toString())
            }
            deailsBooksFragment.arguments = args
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView2, deailsBooksFragment)
                .addToBackStack(null)
                .commit()

        } catch (e: Exception) {
            Log.d("ErrorNavCategory", e.message.toString())
        }
    }
}