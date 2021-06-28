package com.example.projekat.first

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.projekat.R
import com.example.projekat.databinding.ListItemBinding
import com.example.projekat.network.Model

class MyAdapter() : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {


    // list that Adapter will show
    var drinks: List<Model> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
            // Notify any registered observers that the data set has changed. This will cause every
            // element in our RecyclerView to be invalidated.
        }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val withDataBinding: ListItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            MyViewHolder.layout,
            parent, false
        )
        return MyViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.also {
            it.drink = drinks[position]
        }
    }

    override fun getItemCount() = drinks.size

    //uzima binding iz list_item.xml
    class MyViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        companion object {
            @LayoutRes
            val layout = R.layout.list_item
        }
    }


}
