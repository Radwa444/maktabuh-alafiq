package com.example.maktabuhalafiq

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.maktabuhalafiq.Adaper.CategoryAdapter
import com.example.maktabuhalafiq.Adaper.ItemProductAdapter
import com.example.maktabuhalafiq.databinding.FragmentCategoryBinding
import com.example.maktabuhalafiq.databinding.FragmentHomeBinding
import com.example.maktabuhalafiq.utils.SpaceItemCtagory
import com.example.maktabuhalafiq.utils.SpaceItemDecoration


class CategoryFragment : Fragment() {
    lateinit var binding:FragmentCategoryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCategoryBinding.inflate(inflater, container, false)
        val items: ArrayList<Int> = ArrayList()
        items.add(R.mipmap.advertisement)
        items.add(R.mipmap.advertisement)
        items.add(R.mipmap.advertisement)
        items.add(R.mipmap.advertisement)
        items.add(R.mipmap.advertisement)
        items.add(R.mipmap.advertisement)
        items.add(R.mipmap.advertisement)
        items.add(R.mipmap.advertisement)

        val adapter = CategoryAdapter(items)
        binding.itemCategory.adapter = adapter
        val spacingInPixels = resources.getDimensionPixelSize(R.dimen.spacingButtonCategory)
        binding.itemCategory.addItemDecoration(SpaceItemCtagory(spacingInPixels))
        return binding.root
    }


}