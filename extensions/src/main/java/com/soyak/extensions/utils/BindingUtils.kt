package com.soyak.extensions.utils

import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import com.soyak.extensions.utils.extensions.load


object BindingUtils {
    @JvmStatic
    @BindingAdapter("app:url")
    fun showImage(view: ImageView, url: String) {
        view.load(url)
    }

    @JvmStatic
    @BindingAdapter("messageType")
    fun messageType(view: LinearLayout, messageType: String) {
        if (messageType.equals("me"))
            view.gravity = Gravity.END
        else
            view.gravity = Gravity.START
    }


    @JvmStatic
    @BindingAdapter(value = ["app:position", "app:listSize"], requireAll = true)
    fun viewGone(view: View, position: Int, size: Int) {
        if (size - 1 == position) {
            //  view.gone()
        }
    }

    @JvmStatic
    @BindingAdapter(value = ["app:progressScaled", "android:max"], requireAll = true)
    fun setProgress(progressBar: ProgressBar, likes: Int, max: Int) {
        progressBar.progress = (likes * max / 5).coerceAtMost(max)
    }
}
