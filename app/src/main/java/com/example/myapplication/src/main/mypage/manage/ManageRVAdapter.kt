package com.example.myapplication.src.main.mypage.manage

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ListManageBinding
import com.example.myapplication.src.main.mypage.manage.manageSelect.ManageSelectActivity

class ManageRVAdapter(private val itemList : ArrayList<ManageData>, val context : Context) : RecyclerView.Adapter<ManageRVAdapter.ViewHolder>() {
    inner class ViewHolder(private val binding: ListManageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ManageData) {
            binding.postImage.setImageResource(data.image)
            binding.postTitle.text = data.title
            binding.postName.text = data.name
            binding.postId.text = data.id
            binding.postMain.text = data.main
            binding.postBtnDonation.setOnClickListener {
                val intent = Intent(context, ManageSelectActivity::class.java)
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewBinding = ListManageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int = itemList.size
}