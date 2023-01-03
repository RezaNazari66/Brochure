package com.bonial.bronchurelist

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bonial.bronchurelist.databinding.ItemBrochureBinding
import com.bonial.domain.models.Brochure
import com.bumptech.glide.Glide

class BrochuresAdapter(private val context: Context) :
    ListAdapter<Brochure, BrochuresAdapter.BrochuresViewHolder>(BrochuresDiffUtils()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrochuresViewHolder {
        val binding =
            ItemBrochureBinding.inflate(LayoutInflater.from(context), parent, false)
        return BrochuresViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BrochuresViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.apply {

            Glide
                .with(context)
                .load(item.image)
                .placeholder(R.drawable.placeholder)
                .fitCenter()
                .into(this.imageView)

            TitleTextView.text = item.retailerName
        }

    }


    class BrochuresViewHolder(val binding: ItemBrochureBinding) : ViewHolder(binding.root)

    class BrochuresDiffUtils : DiffUtil.ItemCallback<Brochure>() {
        override fun areItemsTheSame(oldItem: Brochure, newItem: Brochure): Boolean {
            return oldItem.image == newItem.image
        }

        override fun areContentsTheSame(oldItem: Brochure, newItem: Brochure): Boolean {
            return oldItem == newItem
        }

    }


}