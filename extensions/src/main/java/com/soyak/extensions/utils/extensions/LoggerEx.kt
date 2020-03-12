package com.soyak.extensions.utils.extensions

import android.app.Activity
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment


fun AppCompatActivity.loggerd(message: String) {
    Log.i(this.localClassName, ":------> $message")
}

fun Activity.loggeri(message: String) {
    Log.d(this.localClassName, message.toString())
}

fun AppCompatActivity.loggere(message: String) {
    Log.e(this.localClassName, message.toString())
}

fun Fragment.loggerd(message: String) {
    Log.d(this.activity?.localClassName, ":------>$message")
}

fun Fragment.logger(message: Int) {
    Log.d(this.activity?.localClassName, message.toString())
}
