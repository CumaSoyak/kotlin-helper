package com.soyak.extensions.utils.extensions

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.View
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView


fun RecyclerView.smoothSnapToPosition(
    position: Int,
    snapMode: Int = LinearSmoothScroller.SNAP_TO_START
) {
    val smoothScroller = object : LinearSmoothScroller(this.context) {
        override fun getVerticalSnapPreference(): Int {
            return snapMode
        }

        override fun getHorizontalSnapPreference(): Int {
            return snapMode
        }
    }
    smoothScroller.targetPosition = position
    layoutManager?.startSmoothScroll(smoothScroller)
}

fun setRecyclerViewFallDownAnimation(itemView: View, i: Int) {

    var i = i

    val isNotFirstItem = i == -1
    i++
    itemView.alpha = 0f

    val animatorSet = AnimatorSet()
    val animator = ObjectAnimator.ofFloat(itemView, "alpha", 0f, 0.5f, 1.0f)

    ObjectAnimator.ofFloat(itemView, "alpha", 0f).start()
    animator.startDelay = if (isNotFirstItem) 300 / 2 else (i * 300 / 3).toLong()
    animator.duration = 500
    animatorSet.play(animator)
    animator.start()


}

fun setRecyclerViewFallDownAnimationTwo(itemView: View, i: Int) {

    var i = i

    val isNotFirstItem = i == -1
    i++
    itemView.alpha = 0f

    val animatorSet = AnimatorSet()
    val animator = ObjectAnimator.ofFloat(itemView, "alpha", 0f, 0.5f, 1.0f)

    ObjectAnimator.ofFloat(itemView, "alpha", 0f).start()
    animator.startDelay = if (isNotFirstItem) 300 / 2 else (i * 300 / 3).toLong()
    animator.duration = 500
    animatorSet.play(animator)
    animator.start()


}