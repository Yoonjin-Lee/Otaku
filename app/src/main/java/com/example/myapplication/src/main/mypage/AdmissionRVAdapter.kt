package com.example.myapplication.src.main.mypage

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ListAdmissionBinding
import com.example.myapplication.src.main.MainActivity
import com.example.myapplication.src.main.mypage.admission.AdmissionActivity
import com.example.myapplication.src.main.mypage.admission.scratch.ScratchActivity

class AdmissionRVAdapter(private val itemList: ArrayList<AdmissionData>, val context : Context) :
    RecyclerView.Adapter<AdmissionRVAdapter.ViewHolder>() {
    inner class ViewHolder(private val binding: ListAdmissionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: AdmissionData) {
            binding.listAdmissionTxtTitle.text = data.title
            binding.listAdmissionTxtDate.text = data.date
            binding.listAdmissionBtn.setOnClickListener {
                val intent = Intent(context, AdmissionActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
                intent.putExtra("eventId", data.eventId)
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewBinding =
            ListAdmissionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int = itemList.size
}
