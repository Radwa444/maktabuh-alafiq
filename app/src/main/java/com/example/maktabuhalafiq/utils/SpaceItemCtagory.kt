package com.example.maktabuhalafiq.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class SpaceItemCtagory(private val spaceHeight: Int) : RecyclerView.ItemDecoration()  {
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        outRect.bottom = spaceHeight
    }
}