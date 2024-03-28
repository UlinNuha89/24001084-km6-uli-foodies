package com.lynn.foodies.presentation.home.adapter

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.lynn.foodies.data.model.Catalog
import com.lynn.foodies.databinding.ItemCatalogBinding
import com.lynn.foodies.utils.toIndonesianFormat

class CatalogGridItemViewHolder(
    private val binding: ItemCatalogBinding,
    private val listener: OnItemClickedListener<Catalog>
) : ViewHolder(binding.root), ViewHolderBinder<Catalog> {
    override fun bind(item: Catalog) {
        item.let {
            binding.ivCatalogImage.load(it.imageUrl){
                crossfade(true)
            }
            binding.tvCatalogName.text = it.name
            binding.tvCatalogPrice.text = it.price.toIndonesianFormat()
            itemView.setOnClickListener {
                listener.onItemClicked(item)
            }
        }

    }
}