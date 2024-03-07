package com.example.maktabuhalafiq.Adaper
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.maktabuhalafiq.R

class ItemBookAdapter(private val imageList:ArrayList<Int>,private val viewPager2: ViewPager2):
    RecyclerView.Adapter<ItemBookAdapter.ItemBookViewHolder>() {
    class ItemBookViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val imageView:ImageView=itemView.findViewById(R.id.item_book)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemBookViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.item_banner_layout,parent,false)
        return ItemBookViewHolder(view)
    }

    override fun getItemCount(): Int {
        return  imageList.size
    }
    override fun onBindViewHolder(holder: ItemBookViewHolder, position: Int) {
        holder.imageView.setImageResource(imageList[position])
        if(position==imageList.size-1){
            viewPager2.post(runnable)
        }
    }
    @SuppressLint("NotifyDataSetChanged")
    private val runnable = Runnable {
        imageList.addAll(imageList)
       notifyDataSetChanged()
    }
}

