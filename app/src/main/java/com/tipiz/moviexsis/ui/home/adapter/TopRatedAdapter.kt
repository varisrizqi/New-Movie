package com.tipiz.moviexsis.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.tipiz.core.domain.model.toprated.DataTopRated
import com.tipiz.moviexsis.R
import com.tipiz.moviexsis.databinding.ItemTopratedBinding
import com.tipiz.moviexsis.utils.Constant

class TopRatedAdapter(private val listener: OnPagingListener) :
    ListAdapter<DataTopRated, TopRatedAdapter.LatestHolder>(DIFF_CALLBACK) {

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DataTopRated>() {
            override fun areItemsTheSame(oldItem: DataTopRated, newItem: DataTopRated): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: DataTopRated, newItem: DataTopRated): Boolean {
                return oldItem == newItem
            }
        }
    }

    class LatestHolder(private val binding: ItemTopratedBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(top: DataTopRated) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(Constant.BACKDROP_PATH + top.posterPath)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.thumbnail_load_product)
                            .error(R.drawable.thumbnail_load_product)
                    )
                    .into(binding.imgItemLatest)
                tvItemTitle.text = top.originalTitle
                tvRating.text = top.voteAverage.toString()

            }
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LatestHolder {
        val binding = ItemTopratedBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LatestHolder(binding)
    }

    override fun onBindViewHolder(holder: LatestHolder, position: Int) {
        val items = getItem(position)
        holder.bind(items)
        holder.itemView.setOnClickListener {
            listener.onClick(items)
        }
    }

    interface OnPagingListener {
        fun onClick(topRated: DataTopRated)
    }
}
