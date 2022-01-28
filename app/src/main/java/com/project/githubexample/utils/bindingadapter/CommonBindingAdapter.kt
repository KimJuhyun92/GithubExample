package com.project.githubexample.utils.bindingadapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide


@BindingAdapter(
    value = ["imageUrl"],
    requireAll = false
)
fun ImageView.loadImage(url: String?) {
    Glide.with(context)
        .load(url)
        .into(this)
}
