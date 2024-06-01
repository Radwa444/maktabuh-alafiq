package com.example.maktabuhalafiq.ui.Adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.maktabuhalafiq.data.models.BooksDownload
import com.example.maktabuhalafiq.databinding.ItemProductDownloadBinding

import com.squareup.picasso.Picasso

class ItemProductDownloadAdapter(
    private var itemList: List<BooksDownload>,
    private val onDownloadClick: (BooksDownload) -> Unit
) : RecyclerView.Adapter<ItemProductDownloadAdapter.ItemProductDownloadViewHolder>() {

    class ItemProductDownloadViewHolder(
        private val binding: ItemProductDownloadBinding,
        private val onDownloadClick: (BooksDownload) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(booksDownload: BooksDownload) {
            Log.d("PicassoImageURL", booksDownload.coverImageUrl)

            Picasso.get()
                .load(booksDownload.coverImageUrl)
                .into(binding.Image)
            binding.author.text = booksDownload.author
            binding.name.text = booksDownload.title
            binding.iconButton.setOnClickListener {

                onDownloadClick(booksDownload)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemProductDownloadViewHolder {
        val binding = ItemProductDownloadBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemProductDownloadViewHolder(binding, onDownloadClick)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ItemProductDownloadViewHolder, position: Int) {
        val booksDownload = itemList[position]
        holder.bind(booksDownload)
    }

    fun updateBooks(newItemList: List<BooksDownload>) {
        itemList = newItemList
        notifyDataSetChanged()
    }
}