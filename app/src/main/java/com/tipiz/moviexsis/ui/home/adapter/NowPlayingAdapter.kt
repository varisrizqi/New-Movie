package com.tipiz.moviexsis.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.tipiz.core.domain.model.nowplaying.DataNowPlaying
import com.tipiz.moviexsis.R
import com.tipiz.moviexsis.databinding.ItemNowPlayingBinding
import com.tipiz.moviexsis.utils.Constant

class NowPlayingAdapter(private val listener: OnPagingListener) :
    ListAdapter<DataNowPlaying, NowPlayingAdapter.NpHolder>(DIFF_CALLBACK) {


    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DataNowPlaying>() {
            override fun areItemsTheSame(
                oldItem: DataNowPlaying,
                newItem: DataNowPlaying
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: DataNowPlaying,
                newItem: DataNowPlaying
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    class NpHolder(private val binding: ItemNowPlayingBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(np: DataNowPlaying) {
            with(binding) {

                Glide.with(itemView.context)
                    .load(Constant.BACKDROP_PATH + np.posterPath)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.thumbnail_load_product)
                            .error(R.drawable.thumbnail_load_product)
                    )
                    .into(binding.imgItemNp)
                tvItemTitle.text = np.originalTitle


            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NpHolder {
        val binding =
            ItemNowPlayingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NpHolder(binding)
    }

    override fun onBindViewHolder(holder: NpHolder, position: Int) {
        val item = getItem(position)

        holder.bind(item)
        holder.itemView.setOnClickListener {
            listener.onClick(item)
        }
    }

    interface OnPagingListener {
        fun onClick(np: DataNowPlaying)
    }
}