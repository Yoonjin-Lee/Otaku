package com.example.myapplication.src.main.mypage.manage.manageParticipate

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ListParticipateBinding

class ParticipateRVAdapter (private val itemList : ArrayList<ParticipateData>, val context : Context) : RecyclerView.Adapter<ParticipateRVAdapter.ViewHolder>() {
    inner class ViewHolder(private val binding: ListParticipateBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ParticipateData) {
            binding.participateName.text = data.name
            binding.participateId.text = data.id
            binding.participateBtnCheck.setOnClickListener {

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewBinding = ListParticipateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int = itemList.size
}