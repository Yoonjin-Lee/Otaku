package com.example.myapplication.src.main.mypage

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ListAdmissionBinding
import com.example.myapplication.databinding.ListPresentBinding
import com.example.myapplication.src.main.mypage.present.PresentActivity

class PresentRVAdapter(private val itemList : ArrayList<AdmissionData>, val context : Context)
    :RecyclerView.Adapter<PresentRVAdapter.ViewHolder>(){

    inner class ViewHolder(private val binding: ListPresentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: AdmissionData) {
            binding.listPresentTxtTitle.text = data.title
            binding.listPresentTxtDate.text = data.date
            binding.listPresentBtn.setOnClickListener {
                val intent = Intent(context, PresentActivity::class.java)
                intent.putExtra("eventId", data.eventId)
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewBinding =
            ListPresentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int = itemList.size
}