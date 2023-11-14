package com.example.myapplication.src.main.mypage.manage.manageDonation

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.config.BaseActivity
import com.example.myapplication.databinding.ListDonationBinding

class DonationRVAdapter(private val itemList : ArrayList<DonationData>, val context : Context) : RecyclerView.Adapter<DonationRVAdapter.ViewHolder>() {
    inner class ViewHolder(private val binding: ListDonationBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var p : Int? = null
        val button : Button = binding.donationBtnCheck
        fun bind(data: DonationData) {
            binding.donationName.text = data.name
            binding.donationMoney.text = data.price
            p = data.supportLogId
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewBinding = ListDonationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(itemList[position])
        holder.button.setOnClickListener {
            val intent = Intent(context, DonationDialog::class.java)
            Log.d("Retrofit", "${holder.p}")
            intent.putExtra("supportLogId", holder.p)
            intent.putExtra("position", position)
            (context as AppCompatActivity).startActivityForResult(intent, 1)
        }
    }

    override fun getItemCount(): Int = itemList.size

}