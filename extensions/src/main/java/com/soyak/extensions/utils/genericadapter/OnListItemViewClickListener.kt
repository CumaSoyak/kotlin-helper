package com.soyak.extensions.utils.genericadapter

import android.view.View
import com.soyak.extensions.utils.genericadapter.ListItemViewModel

interface OnListItemViewClickListener {
    fun onClick(view: View, position: Int, model: ListItemViewModel) {}
    fun onClickDetail(view: View, position: Int, model: ListItemViewModel) {}
}