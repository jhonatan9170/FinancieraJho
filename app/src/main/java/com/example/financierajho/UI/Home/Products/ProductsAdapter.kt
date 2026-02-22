package com.example.financierajho.UI.Home.Products

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.financierajho.databinding.ItemProductLayoutBinding

class ProductsAdapter(private var list: List<ProductModel>):
    RecyclerView.Adapter<ProductItemViewHolder>() {

    override fun getItemCount(): Int {
        return list.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductItemViewHolder {
        val binding = ItemProductLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductItemViewHolder, position: Int) {
        val item = list[position]
        if (item.isActive) {
            holder.binding.nameAccountTxt.text = item.name
            holder.binding.subtitleTxt.text = "S/ " + item.balance
        }
        else{
            holder.binding.nameAccountTxt.text = "Abre tu " + item.name
            holder.binding.balanceContainer.visibility = View.GONE
        }
    }

    fun updateList(newList: List<ProductModel>){
        list = newList
        notifyDataSetChanged()
    }

}