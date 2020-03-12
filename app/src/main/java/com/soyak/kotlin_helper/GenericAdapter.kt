package com.soyak.kotlin_helper

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import com.soyak.extensions.utils.genericadapter.ListItemViewModel
import com.soyak.extensions.utils.genericadapter.OnListItemViewClickListener

class GenericAdapter<T : ListItemViewModel>(@LayoutRes val layoutId: Int, val listener: OnListItemViewClickListener? = null) :
    RecyclerView.Adapter<GenericAdapter.GenericViewHolder<T>>() {

    private val items = mutableListOf<T>()
    private var inflater: LayoutInflater? = null
    private var onListItemViewClickListener = listener

    fun addItems(items: ArrayList<T>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun setOnListItemViewClickListener(onListItemViewClickListener: OnListItemViewClickListener?) {
        this.onListItemViewClickListener = onListItemViewClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericViewHolder<T> {
        val layoutInflater = inflater ?: LayoutInflater.from(parent.context)
        val binding =
            DataBindingUtil.inflate<ViewDataBinding>(layoutInflater, layoutId, parent, false)
        return GenericViewHolder(
            binding
        )
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: GenericViewHolder<T>, position: Int) {
        val itemViewModel = items[position]
        itemViewModel.adapterPosition = position
        itemViewModel.itemSize = items.size
        onListItemViewClickListener?.let { itemViewModel.onListItemViewClickListener = it }
        holder.bind(itemViewModel)
    }

    class GenericViewHolder<T : ListItemViewModel>(private val binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(itemViewModel: T) {
            binding.setVariable(BR.vm, itemViewModel)
            binding.executePendingBindings()
        }
    }
}