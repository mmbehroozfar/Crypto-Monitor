package com.mmb.ui.asset.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mmb.ui.asset.databinding.ItemAssetBinding
import com.mmb.ui.asset.model.AssetSummary

class AssetPagingAdapter(
    private val onItemClicked: (String) -> Unit,
    private val onFavoriteClicked: (AssetSummary) -> Unit,
) : PagingDataAdapter<AssetSummary, AssetPagingAdapter.AssetViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AssetViewHolder {
        val binding = ItemAssetBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

        return AssetViewHolder(
            binding = binding,
            onItemClicked = onItemClicked,
            onFavoriteClicked = onFavoriteClicked,
        )
    }

    override fun onBindViewHolder(holder: AssetViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    inner class AssetViewHolder(
        private val binding: ItemAssetBinding,
        private val onItemClicked: (String) -> Unit,
        private val onFavoriteClicked: (AssetSummary) -> Unit,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: AssetSummary) {
            binding.apply {
                asset = item
                root.setOnClickListener {
                    onItemClicked(item.id)
                }
                favoriteIb.setOnClickListener {
                    onFavoriteClicked(item)
                }
                executePendingBindings()
            }
        }

    }

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<AssetSummary>() {
            override fun areItemsTheSame(oldItem: AssetSummary, newItem: AssetSummary): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: AssetSummary, newItem: AssetSummary): Boolean {
                return oldItem.name == newItem.name && oldItem.isFavorite == newItem.isFavorite
            }
        }
    }

}