package com.soyak.extensions.utils.genericadapter


abstract class ListItemViewModel {
    var adapterPosition: Int = -1
    var onListItemViewClickListener: OnListItemViewClickListener? = null
    var itemSize: Int = -1
    val list = mutableListOf<Any>()
}