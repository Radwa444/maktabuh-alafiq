package com.example.maktabuhalafiq

import Book
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.maktabuhalafiq.databinding.FragmentBooksBinding
import com.example.maktabuhalafiq.databinding.FragmentDeailsBookBinding
import com.example.maktabuhalafiq.ui.book.BooksFragment
import com.example.maktabuhalafiq.ui.category.CategoryFragment
import com.squareup.picasso.Picasso


class DeailsBookFragment : Fragment() {
    private lateinit var binding: FragmentDeailsBookBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         binding = FragmentDeailsBookBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val book = arguments?.getParcelable<Book>("book_key")
        if (book != null) {
            Picasso.get()
                .load(book.image)
                .into(binding.ImageBook)
            binding.titleBook.text="${book.title}اسم الكتاب:"
            binding.authorBook.text=" ${book.author}المؤلف:"
            binding.categoryBook.text="${book.publisher}الصنف:"
            binding.numberPart.text="${book.pages}عدد الصفحات:"
            binding.buying.text=book.price.toString()
        } else {
            Log.e("DetailsBookFragment", "No book received")
        }
        onClickBack()
    }

    private fun onClickBack() {
        binding.buttomBack.setOnClickListener {  requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView2, BooksFragment())
            .addToBackStack(null)
            .commit() }
    }


}