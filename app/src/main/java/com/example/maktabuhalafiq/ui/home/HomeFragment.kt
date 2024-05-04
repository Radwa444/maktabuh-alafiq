package com.example.maktabuhalafiq.ui.home

import Book
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.maktabuhalafiq.R
import com.example.maktabuhalafiq.ui.Adapter.ItemBookAdapter
import com.example.maktabuhalafiq.ui.Adapter.ItemProductAdapter
import com.example.maktabuhalafiq.ui.Adapter.ItemProductDownloadAdapter
import com.example.maktabuhalafiq.databinding.FragmentHomeBinding
import com.example.maktabuhalafiq.data.models.ItemPorduct
import com.example.maktabuhalafiq.data.models.ItemProductDownload
import com.example.maktabuhalafiq.ui.Adapter.ButtonCategoriesAdapter
import com.example.maktabuhalafiq.ui.book.BooksFragment
import com.example.maktabuhalafiq.utils.SpaceItemDecoration

import com.example.maktabuhalafiq.utils.UiState
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.abs

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var viewPager2: ViewPager2
    private lateinit var recyclerView: RecyclerView
    private lateinit var handler: Handler
    private lateinit var imageList: ArrayList<Int>
    private lateinit var adapter: ItemBookAdapter
    private val categoryViewModel: HomeViewModel by viewModels()
    private lateinit var  buttonCategoriesAdapter: ButtonCategoriesAdapter

    lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        viewPager2 = binding.viewPager2
        recyclerView=binding.buttonCategories
        init()
        setUpTransformer()
        buttonCategory()
        productItem()


        return binding.root
    }

    private fun productItem() {
        val items = listOf(
            ItemPorduct(R.mipmap.book1, "ورق الشجره", "خلود محمود", "20جنيه"),
            ItemPorduct(R.mipmap.book2, "عناقيد الذهب", "أحمد علي", "25جنيه"),
            ItemPorduct(R.mipmap.book1, "ورق الشجره", "خلود محمود", "20جنيه"),
            ItemPorduct(R.mipmap.book2, "عناقيد الذهب", "أحمد علي", "25جنيه"),

        )
        val items2 = listOf(
          ItemProductDownload(R.mipmap.book4,"الفئران لا تدخل الجنة", "سعيد أبو طالب",),
            ItemProductDownload(R.mipmap.book5,"رنا", "محمد مجدي يونس",),
            ItemProductDownload(R.mipmap.book7,"اخر محاولة", "محمود منير",),

            )

        val adapter = ItemProductAdapter(items)
        binding.bestSeller.adapter=adapter
        binding.mostRated.adapter=adapter
        binding.mostRated.addItemDecoration(SpaceItemDecoration(resources.getDimensionPixelSize(R.dimen.dimenButtonCategories)))
        binding.bestSeller.addItemDecoration(SpaceItemDecoration(resources.getDimensionPixelSize(R.dimen.dimenButtonCategories)))
       val adapter2= ItemProductDownloadAdapter(items2)
       binding.itemDownload.adapter=adapter2
        binding.itemDownload.addItemDecoration(SpaceItemDecoration(resources.getDimensionPixelSize(R.dimen.dimenButtonCategories)))
    }

    private fun buttonCategory() {
        buttonCategoriesAdapter = ButtonCategoriesAdapter { category ->
            try {
                navigateToBooks(category.books)

            }catch (e:Exception){
                Log.d("HomeFragment",e.message.toString())
            }

            Log.d("HomeFragment", category.books.toString())
        }
        binding.buttonCategories.apply {

            adapter = buttonCategoriesAdapter
            addItemDecoration(SpaceItemDecoration(resources.getDimensionPixelSize(R.dimen.dimenButtonCategories)))
        }

        observeCategories()

        categoryViewModel.fetchCategories()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handler = Handler(Looper.getMainLooper())
        handler.postDelayed(runnable, 2000)
        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                handler.removeCallbacks(runnable)
                handler.postDelayed(runnable, 2000)
            }
        })
    }

    private val runnable = Runnable {
        viewPager2.currentItem += 1
    }

    private fun init() {
        imageList = ArrayList()

        imageList.add(R.mipmap.book1)
        imageList.add(R.mipmap.book2)
        imageList.add(R.mipmap.book3)
        adapter = ItemBookAdapter(imageList,viewPager2)
        viewPager2.adapter = adapter
        viewPager2.offscreenPageLimit=3
        viewPager2.clipChildren=false
        viewPager2.clipToPadding=false
        viewPager2.getChildAt(0).overScrollMode=RecyclerView.OVER_SCROLL_NEVER
    }

    private fun setUpTransformer() {
        val transformer = CompositePageTransformer()
        transformer.addTransformer(MarginPageTransformer(40))
        transformer.addTransformer { page, position ->
            val r = 1 - abs(position)
            page.scaleY = 0.85f + r * 0.14f
        }
        viewPager2.setPageTransformer(transformer)
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(runnable)
    }

    override fun onResume() {
        super.onResume()
        handler.postDelayed(runnable, 2000)
    }
    private fun observeCategories() {
        categoryViewModel.categories.observe(viewLifecycleOwner) { state ->
            when (state) {
                is UiState.Success -> {
                    val categories = state.data
                    buttonCategoriesAdapter.submitList(categories)
                }
                is UiState.Failure -> {
                    Log.e("CategoryFragment", "Failed to fetch categories: ${state.error}")
                }

                else -> {

                }
            }
        }
    }
    private fun navigateToBooks(book:List<Book>) {
        try {
            val booksFragment = BooksFragment()

            val args = Bundle().apply {
                putParcelableArrayList("list", ArrayList(book))
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