package com.soyak.extensions.utils.extensions

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.soyak.extensions.R

fun AppCompatActivity.overridePendingTransitionEnter() {
    overridePendingTransition(R.anim.right_to_left_in, R.anim.right_to_left_exit)
}


fun AppCompatActivity.overridePendingTransitionExit() {
    overridePendingTransition(R.anim.left_to_right_in, R.anim.left_to_right_exit)
}

fun Fragment.overridePendingTransitionEnter() {
    this.activity?.overridePendingTransition(R.anim.right_to_left_in, R.anim.right_to_left_exit)
}
