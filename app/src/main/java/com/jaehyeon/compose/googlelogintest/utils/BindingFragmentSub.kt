package com.jaehyeon.compose.googlelogintest.utils

import android.annotation.SuppressLint
import android.widget.TextView
import androidx.databinding.BindingAdapter

@SuppressLint("SetTextI18n")
@BindingAdapter("setId")
fun bindSetId(tv: TextView, id: String?) {
    id?: return

    tv.text = "id : $id"
}

@SuppressLint("SetTextI18n")
@BindingAdapter("setEmail")
fun bindSetEmail(tv: TextView, email: String?) {
    email?: return

    tv.text = "email : $email"
}