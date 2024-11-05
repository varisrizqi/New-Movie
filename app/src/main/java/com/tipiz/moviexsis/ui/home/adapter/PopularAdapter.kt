package com.tipiz.moviexsis.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.tipiz.core.domain.model.popular.DataPopular
import com.tipiz.moviexsis.R
import com.tipiz.moviexsis.databinding.ItemPopularBinding
import com.tipiz.moviexsis.utils.Constant.BACKDROP_PATH

class PopularAdapter(
    private val popular: List<DataPopular>,
    private val listener: OnPagingListener
) :
    RecyclerView.Adapter<PopularAdapter.HomeHolder>() {

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DataPopular>() {
            override fun areItemsTheSame(oldItem: DataPopular, newItem: DataPopular): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: DataPopular, newItem: DataPopular): Boolean {
                return oldItem == newItem
            }
        }
    }

    class HomeHolder(private val binding: ItemPopularBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(popular: DataPopular) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(BACKDROP_PATH + popular.posterPath)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.thumbnail_load_product)
                            .error(R.drawable.thumbnail_load_product)
                    )
                    .into(binding.imgItemPopular)

                tvItemTitle.text = popular.originalTitle
                tvItemDesc.text = popular.overview

            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeHolder {
        val binding = ItemPopularBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeHolder(binding)
    }

    override fun getItemCount(): Int = 5

    override fun onBindViewHolder(holder: HomeHolder, position: Int) {
        val popular = popular[position]

        holder.bind(popular)
        holder.itemView.setOnClickListener {
            listener.onClick(popular)
        }
    }

    interface OnPagingListener {
        fun onClick(popular: DataPopular)
    }
}