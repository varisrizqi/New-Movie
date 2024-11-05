package com.tipiz.moviexsis.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.tipiz.core.domain.model.search.DataSearch
import com.tipiz.moviexsis.R
import com.tipiz.moviexsis.databinding.ItemNowPlayingBinding
import com.tipiz.moviexsis.utils.Constant

class SearchAdapter(private val listener: OnPagingListener) :
    ListAdapter<DataSearch, SearchAdapter.SearchHolder>(DIFF_CALLBACK) {


    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DataSearch>() {
            override fun areItemsTheSame(
                oldItem: DataSearch,
                newItem: DataSearch
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: DataSearch,
                newItem: DataSearch
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchHolder {
        val binding =
            ItemNowPlayingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchHolder, position: Int) {
        val item = getItem(position)

        holder.bind(item)
        holder.itemView.setOnClickListener {
            listener.onClick(item)
        }
    }

    interface OnPagingListener {
        fun onClick(search: DataSearch)
    }

    class SearchHolder(private val binding: ItemNowPlayingBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(search: DataSearch) {
            with(binding) {

                Glide.with(itemView.context)
                    .load(Constant.BACKDROP_PATH + search.posterPath)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.thumbnail_load_product)
                            .error(R.drawable.thumbnail_load_product)
                    )
                    .into(binding.imgItemNp)
                tvItemTitle.text = search.originalTitle
            }
        }

    }
}