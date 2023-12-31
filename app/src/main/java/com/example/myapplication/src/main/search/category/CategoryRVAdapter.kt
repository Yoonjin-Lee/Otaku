package com.example.myapplication.src.main.search.category

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ListCategoryBinding

class CategoryRVAdapter(private val cList : ArrayList<CData>) : RecyclerView.Adapter<CategoryRVAdapter.ViewHolder>() {
    inner class ViewHolder(private val binding: ListCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: CData) {
            binding.listCategory.text = data.title
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryRVAdapter.ViewHolder {
        val viewBinding =
            ListCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: CategoryRVAdapter.ViewHolder, position: Int) {
        holder.bind(cList[position])
        holder.itemView.setOnClickListener {
            categoryClickListener.onClick(it, position, cList[position])
        }
    }

    override fun getItemCount(): Int = cList.size

    interface OnCategoryClickListener {
        fun onClick(v: View, position: Int, data: CData)
    }

    fun setCategoryClickListener(onItemClickListener: OnCategoryClickListener) {
        this.categoryClickListener = onItemClickListener
    }

    private lateinit var categoryClickListener: OnCategoryClickListener

}