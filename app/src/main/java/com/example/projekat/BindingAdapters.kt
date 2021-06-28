package com.example.projekat

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.projekat.first.MyAdapter
import com.example.projekat.network.Model

/**
 * Binding adapter used to hide the spinner once data is available.
 */
@BindingAdapter("isNetworkError", "lista")
fun hideIfNetworkError(view: View, isNetWorkError: Boolean, lista: Any?) {
    view.visibility = if (lista != null) View.GONE else View.VISIBLE

    if(isNetWorkError) {
        view.visibility = View.GONE
    }
}