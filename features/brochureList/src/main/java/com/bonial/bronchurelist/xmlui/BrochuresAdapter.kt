package com.bonial.bronchurelist.xmlui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bonial.bronchurelist.R
import com.bonial.bronchurelist.databinding.ItemBrochureBinding
import com.bonial.bronchurelist.databinding.ItemPremiumBrochureBinding
import com.bonial.domain.models.Brochure
import com.bonial.domain.models.ContentTypeEnum
import com.bumptech.glide.Glide

const val BROCHURE_VIEW_TYPE = 1
const val BROCHURE_PREMIUM_VIEW_TYPE = 2

class BrochuresAdapter(private val context: Context) :
    ListAdapter<Brochure, ViewHolder>(BrochuresDiffUtils()) {


    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return if (item.contentType == ContentTypeEnum.BROCHURE_PREMIUM) BROCHURE_PREMIUM_VIEW_TYPE else BROCHURE_VIEW_TYPE
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return if (viewType == BROCHURE_VIEW_TYPE) {
            val binding = ItemBrochureBinding.inflate(LayoutInflater.from(context), parent, false)
            BrochuresViewHolder(binding)
        } else {
            val binding =
                ItemPremiumBrochureBinding.inflate(LayoutInflater.from(context), parent, false)
            BrochuresPremiumViewHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        if (item.contentType == ContentTypeEnum.BROCHURE_PREMIUM) {
            (holder as BrochuresPremiumViewHolder).bind(item)
        } else {
            (holder as BrochuresViewHolder).bind(item)
        }

    }

    inner class BrochuresViewHolder(private val binding: ItemBrochureBinding) :
        ViewHolder(binding.root) {
        fun bind(item: Brochure) {
            Glide
                .with(context)
                .load(item.image)
                .placeholder(R.drawable.placeholder)
                .fitCenter()
                .into(binding.imageView)
            binding.titleTextView.text = item.retailerName
        }
    }

    inner class BrochuresPremiumViewHolder(private val binding: ItemPremiumBrochureBinding) :
        ViewHolder(binding.root) {
        fun bind(item: Brochure) {
            Glide
                .with(context)
                .load(item.image)
                .placeholder(R.drawable.placeholder)
                .centerCrop()
                .into(binding.imageView)
            binding.titleTextView.text = item.retailerName
        }
    }

    class BrochuresDiffUtils : DiffUtil.ItemCallback<Brochure>() {
        override fun areItemsTheSame(oldItem: Brochure, newItem: Brochure): Boolean {
            return oldItem.image == newItem.image
        }

        override fun areContentsTheSame(oldItem: Brochure, newItem: Brochure): Boolean {
            return oldItem == newItem
        }

    }


}