package com.example.maktabuhalafiq.ui.home

import Book
import android.annotation.SuppressLint
import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.maktabuhalafiq.ui.favoriteBooks.FavoriteBooksFragment
import com.example.maktabuhalafiq.R
import com.example.maktabuhalafiq.data.models.BooksDownload
import com.example.maktabuhalafiq.ui.Adapter.ItemBookAdapter
import com.example.maktabuhalafiq.ui.Adapter.ItemProductAdapter
import com.example.maktabuhalafiq.ui.Adapter.ItemProductDownloadAdapter
import com.example.maktabuhalafiq.databinding.FragmentHomeBinding
import com.example.maktabuhalafiq.data.models.ItemPorduct
import com.example.maktabuhalafiq.ui.Adapter.ButtonCategoriesAdapter
import com.example.maktabuhalafiq.ui.book.BooksFragment
import com.example.maktabuhalafiq.ui.common.views.ProgressDialog
import com.example.maktabuhalafiq.utils.SpaceItemDecoration

import com.example.maktabuhalafiq.utils.UiState
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.abs

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var viewPager2: ViewPager2
    private lateinit var recyclerView: RecyclerView
    private lateinit var handler: Handler
    private lateinit var imageList: ArrayList<Int>
    private lateinit var adapter: ItemBookAdapter
    private lateinit var booksDownloaAdapter: ItemProductDownloadAdapter
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var downloadCompleteReceiver: BroadcastReceiver
    private var downloadId: Long = -1L

    private val ViewModel: HomeViewModel by viewModels()
    private lateinit var  buttonCategoriesAdapter: ButtonCategoriesAdapter
    val progressDialog by lazy {
        ProgressDialog.showDownloadCompleteDialog(requireContext())
    }
    lateinit var binding: FragmentHomeBinding
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)



        viewPager2 = binding.viewPager2
        recyclerView=binding.buttonCategories
        val view = binding.navigationView.getHeaderView(0)
        val textView = view.findViewById<TextView>(R.id.textView57)
        val navFavorite=view.findViewById<LinearLayout>(R.id.favorite_books)

        navFavorite.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView2, FavoriteBooksFragment())
                .addToBackStack(null)
                .commit()
        }
        textView.text = "Radwa saeed"





        init()
        setUpTransformer()
        buttonCategory()
        productItem()
        observerBooksDownload()
        drawerLayout = binding.drawerLayout
        setupNavigationDrawer()
        drawableNavigtion()
        booksDownloaAdapter = ItemProductDownloadAdapter(emptyList()) { booksDownload ->
            downloadBook(booksDownload)
        }
        binding.itemDownload.adapter = booksDownloaAdapter
        observerBooksDownload()


        downloadCompleteReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                val id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1)
                if (id == downloadId) {
                    progressDialog
                }
            }
        }
        requireContext().registerReceiver(downloadCompleteReceiver, IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE),
            Context.RECEIVER_NOT_EXPORTED)

        return binding.root
    }

    private fun setupNavigationDrawer() {
        val navView: NavigationView = binding.navigationView

        //navView.setNavigationItemSelectedListener {
//            when (it.itemId) {
//                R.id.Favorite_books -> {
//                    // Handle home click
//                }
//                R.id.Download_books -> {
//                    // Handle profile click
//                }
//                R.id.Publish_a_book -> {
//                    // Handle settings click
//                }
//                R.id.Connect_with_us -> {
//                    // Handle settings click
//                }
//                R.id.who_are_we -> {
//                    // Handle settings click
//                }
//                R.id.Settings -> {
//                    // Handle settings click
//                }
//            }
//            drawerLayout.closeDrawers()
//            true
//        }
    }


    private fun drawableNavigtion() {

        binding.apply {
            menuIcon.setOnClickListener {
                if (drawerLayout.isDrawerOpen(GravityCompat.END)) {
                    drawerLayout.closeDrawer(GravityCompat.END)
                } else {
                    drawerLayout.openDrawer(GravityCompat.END)
                }

            }

        }

    }

    @SuppressLint("ServiceCast")
    private fun downloadBook(booksDownload: BooksDownload) {
        val url = booksDownload.downloadUrl
        val title = booksDownload.title

        val downloadManager = requireContext().getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        val uri = Uri.parse(url)
        val request = DownloadManager.Request(uri)
        request.setTitle(title)
        request.setDescription("Downloading $title")
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "$title.pdf")

        downloadManager.enqueue(request)
        progressDialog
        Toast.makeText(requireContext(), "Downloading $title", Toast.LENGTH_SHORT).show()
    }
    private fun productItem() {
        val items = listOf(
            ItemPorduct(R.mipmap.book1, "ورق الشجره", "خلود محمود", "20جنيه"),
            ItemPorduct(R.mipmap.book2, "عناقيد الذهب", "أحمد علي", "25جنيه"),
            ItemPorduct(R.mipmap.book1, "ورق الشجره", "خلود محمود", "20جنيه"),
            ItemPorduct(R.mipmap.book2, "عناقيد الذهب", "أحمد علي", "25جنيه"),

            )



        val adapter = ItemProductAdapter(items)
        binding.bestSeller.adapter=adapter
        binding.mostRated.adapter=adapter
        binding.mostRated.addItemDecoration(SpaceItemDecoration(resources.getDimensionPixelSize(R.dimen.dimenButtonCategories)))
        binding.bestSeller.addItemDecoration(SpaceItemDecoration(resources.getDimensionPixelSize(R.dimen.dimenButtonCategories)))

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

        ViewModel.fetchCategories()

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
        ViewModel.categories.observe(viewLifecycleOwner) { state ->
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
    private fun observerBooksDownload() {




        ViewModel.booksDownload.observe(viewLifecycleOwner) { state ->
            when (state) {
                is UiState.Success -> {
                    booksDownloaAdapter.updateBooks(state.data)
                }
                is UiState.Failure -> {
                    Log.e("BookDownloadFragment", "Failed to fetch books: ${state.error}")
                }
                else -> {
                    // Loading or Idle state
                }
            }
        }

        ViewModel.fetchBooksDownload()
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