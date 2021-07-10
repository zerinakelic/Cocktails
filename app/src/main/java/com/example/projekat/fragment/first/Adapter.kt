package com.example.projekat.fragment.first

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.projekat.R
import com.example.projekat.databinding.ListItemBinding
import com.example.projekat.network.Model

class MyAdapter(private val clickListener: ItemClickListener) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

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
        return MyViewHolder(withDataBinding).listen { position, _ ->
            clickListener.onCocktailClicked(drinks[position].strDrink)
        }
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.also {
            it.drink = drinks[position]
        }
    }

    override fun getItemCount() = drinks.size

    class MyViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        companion object {
            @LayoutRes
            val layout = R.layout.list_item
        }
    }

    private fun <T : RecyclerView.ViewHolder> T.listen(event: (position: Int, type: Int) -> Unit): T {
        itemView.setOnClickListener {
            event.invoke(adapterPosition, itemViewType)
        }
        return this
    }


}
