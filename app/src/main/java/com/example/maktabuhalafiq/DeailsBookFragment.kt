package com.example.maktabuhalafiq

import Book
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.maktabuhalafiq.data.datasource.datastore.DataStoreManager
import com.example.maktabuhalafiq.databinding.FragmentBooksBinding
import com.example.maktabuhalafiq.databinding.FragmentDeailsBookBinding
import com.example.maktabuhalafiq.ui.book.BooksFragment
import com.example.maktabuhalafiq.ui.category.CategoryFragment
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch


class DeailsBookFragment : Fragment() {
    private lateinit var binding: FragmentDeailsBookBinding
    private lateinit var dataStoreManager: DataStoreManager
    private var book: Book? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDeailsBookBinding.inflate(inflater, container, false)
        dataStoreManager = DataStoreManager(requireContext())
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        book = arguments?.getParcelable("book_key")
        book?.let {
            Picasso.get().load(it.image).into(binding.ImageBook)
            binding.titleBook.text = "اسم الكتاب: ${it.title}"
            binding.authorBook.text = "المؤلف: ${it.author}"
            binding.categoryBook.text = "الصنف: ${it.publisher}"
            binding.numberPart.text = "عدد الصفحات: ${it.pages}"
            binding.buying.text = it.price.toString()
            observeArchivesStatus(it.id.toString())
            observeFavoriteStatus(it.id.toString())
            setupClickListeners(it.id.toString())
        } ?: Log.e("DetailsBookFragment", "No book received")
        onClickBack()
    }

    private fun observeFavoriteStatus(bookId: String) {
        viewLifecycleOwner.lifecycleScope.launch {
            dataStoreManager.favoriteBooksFlow.collect { favoriteBooks ->
                val isFavorite = favoriteBooks.contains(bookId)
                binding.buttomFavorite.setImageResource(
                    if (isFavorite) R.drawable.baseline_favorite_24 else R.drawable.__icon__heart_
                )
            }
        }
    }
    private fun observeArchivesStatus(bookId: String) {
        viewLifecycleOwner.lifecycleScope.launch {
            dataStoreManager.archivesBooksFlow.collect { archivesBooks ->
                val isArchives = archivesBooks.contains(bookId)
                binding.buttonSave.setImageResource(
                    if (isArchives) R.drawable.ic_archives_22 else R.drawable.ison_bookmark
                )
            }
        }
    }

    private fun setupClickListeners(bookId: String) {
        binding.buttomFavorite.setOnClickListener {
            viewLifecycleOwner.lifecycleScope.launch {
                dataStoreManager.toggleFavoriteBook(bookId)
            }
        }
        binding.buttonSave.setOnClickListener {
            viewLifecycleOwner.lifecycleScope.launch {
                dataStoreManager.toggleArchivesBook(bookId)
            }
        }
    }


    private fun onClickBack() {
        binding.buttomBack.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }
}