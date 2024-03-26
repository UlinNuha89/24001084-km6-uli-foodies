package com.lynn.foodies.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.lynn.foodies.base.ViewHolderBinder
import com.lynn.foodies.data.model.Catalog
import com.lynn.foodies.databinding.ItemCatalogBinding
import com.lynn.foodies.databinding.ItemCatalogListBinding

class CatalogAdapter(
    private val listener: OnItemClickedListener<Catalog>,
    private val listMode: Int = MODE_LIST
) : RecyclerView.Adapter<ViewHolder>() {

    companion object {
        const val MODE_LIST = 0
        const val MODE_GRID = 1
    }

    private var asyncDataDiffer = AsyncListDiffer(
        this, object : DiffUtil.ItemCallback<Catalog>() {
            override fun areItemsTheSame(oldItem: Catalog, newItem: Catalog): Boolean {
                //membandingkan apakah item sama
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: Catalog, newItem: Catalog): Boolean {
                //membandingkan konten nya
                return oldItem.hashCode() == newItem.hashCode()
            }

        }
    )

    fun submitData(data: List<Catalog>) {
        asyncDataDiffer.submitList(data)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return if (listMode == MODE_GRID)
            CatalogGridItemViewHolder(
                ItemCatalogBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                ), listener
            ) else {
            CatalogListItemViewHolder(
                ItemCatalogListBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                ), listener
            )
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (holder !is ViewHolderBinder<*>) return
        (holder as ViewHolderBinder<Catalog>).bind(asyncDataDiffer.currentList[position])
    }

    override fun getItemCount(): Int = asyncDataDiffer.currentList.size
}

interface OnItemClickedListener<T> {
    fun onItemClicked(item: T)
}